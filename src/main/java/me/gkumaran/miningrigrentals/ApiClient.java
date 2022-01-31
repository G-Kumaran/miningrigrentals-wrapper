package me.gkumaran.miningrigrentals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Builder;
import me.gkumaran.miningrigrentals.constant.TYPE;
import me.gkumaran.miningrigrentals.domain.WhoAmI;
import me.gkumaran.miningrigrentals.domain.account.Account;
import me.gkumaran.miningrigrentals.domain.account.Balance;
import me.gkumaran.miningrigrentals.domain.account.Transaction;
import me.gkumaran.miningrigrentals.domain.account.Transactions;
import me.gkumaran.miningrigrentals.domain.account.inputs.TransactionsFilter;
import me.gkumaran.miningrigrentals.domain.info.Algorithm;
import me.gkumaran.miningrigrentals.domain.info.Currencies;
import me.gkumaran.miningrigrentals.domain.info.Server;
import me.gkumaran.miningrigrentals.domain.rental.ExtendResponse;
import me.gkumaran.miningrigrentals.domain.rental.Messages;
import me.gkumaran.miningrigrentals.domain.rental.Rental;
import me.gkumaran.miningrigrentals.domain.rental.Rentals;
import me.gkumaran.miningrigrentals.domain.rental.inputs.MessageConfig;
import me.gkumaran.miningrigrentals.domain.rental.inputs.RentalFilter;
import me.gkumaran.miningrigrentals.domain.rig.Port;
import me.gkumaran.miningrigrentals.domain.rig.Rig;
import me.gkumaran.miningrigrentals.domain.rig.RigPool;
import me.gkumaran.miningrigrentals.domain.rig.Rigs;
import me.gkumaran.miningrigrentals.domain.rig.Threads;
import me.gkumaran.miningrigrentals.domain.rig.input.BatchConfig;
import me.gkumaran.miningrigrentals.domain.rig.input.ExtendConfig;
import me.gkumaran.miningrigrentals.domain.rig.input.PoolConfig;
import me.gkumaran.miningrigrentals.domain.rig.input.RigConfig;
import me.gkumaran.miningrigrentals.domain.rig.input.RigFilter;
import me.gkumaran.miningrigrentals.domain.riggroup.RigGroup;

@Builder
public class ApiClient
{
	private ApiService miningRigRentalsApiService;
	private Api miningRigRentalsApi;

	/**
	 * <p>
	 * GET /whoami
	 * </p>
	 * 
	 * @since 1.0.0
	 */
	public WhoAmI WhoAmI()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.WhoAmI());
	}

	/**
	 * <p>
	 * GET /info/servers
	 * </p>
	 * 
	 * @since 1.0.0
	 */
	public List<Server> getServers()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getServers());
	}

	/**
	 * <p>
	 * GET /info/algos
	 * </p>
	 * 
	 * @since 1.0.0
	 */
	public List<Algorithm> getAlgorithms()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getAlgorithms());
	}

	/**
	 * <p>
	 * GET /info/algos
	 * </p>
	 * 
	 * @param algorithm Algorithm to Filter
	 * @param currency  Currency to Filter
	 * 
	 * @since 1.0.0
	 */
	public Algorithm getAlgorithm(String algorithm, String currency)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getAlgorithm(algorithm, currency));
	}

	/**
	 * <p>
	 * GET /info/algos
	 * </p>
	 * 
	 * @param algorithm Algorithm to Filter
	 * 
	 * @since 1.0.0
	 */
	public Algorithm getAlgorithm(String algorithm)
	{
		return getAlgorithm(algorithm, null);
	}

	/**
	 * <p>
	 * GET /info/currencies
	 * </p>
	 * 
	 * @since 1.0.0
	 */
	public Currencies getCurrencies()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getCurrencies());
	}

	/**
	 * <p>
	 * GET /account
	 * </p>
	 * 
	 * @since 1.0.0
	 */
	public Account getAccount()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getAccount());
	}

	/**
	 * <p>
	 * GET /account/balance
	 * </p>
	 * 
	 * @since 1.0.0
	 */
	public Map<String, Balance> getBalances()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getBalances());
	}

	/**
	 * <p>
	 * GET /account/transactions
	 * </p>
	 * USE WITH CAUTION
	 * 
	 * @param transactionsFilter {@link me.gkumaran.miningrigrentals.domain.account.inputs.TransactionsFilter}
	 * 
	 * @return All transactions matching the filter without MRR Paging
	 * 
	 * @since 1.0.0
	 */
	public List<Transaction> getTransactions(TransactionsFilter transactionsFilter)
	{
		List<Transaction> transactionList = new ArrayList<Transaction>();
		Integer total = 0;
		do
		{
			transactionsFilter.setLimit(100);
			transactionsFilter.setStart(transactionList.size());
			Transactions pagedTransactions = getTransactionsPaged(transactionsFilter);
			total = pagedTransactions.getTotal();
			transactionList.addAll(pagedTransactions.getTransactions());
		} while (transactionList.size() < total);
		return transactionList;
	}

	/**
	 * <p>
	 * GET /account/transactions
	 * </p>
	 * USE WITH CAUTION
	 * 
	 * @return All transactions without MRR Paging
	 * 
	 * @since 1.0.0
	 */
	public List<Transaction> getTransactions()
	{
		return getTransactions(TransactionsFilter   .builder()
													.build());
	}

	/**
	 * <p>
	 * GET /account/transactions
	 * </p>
	 * 
	 * @param transactionsFilter {@link me.gkumaran.miningrigrentals.domain.account.inputs.TransactionsFilter}
	 * 
	 * @return Transactions matching the filter with MRR Paging
	 * 
	 * @since 1.0.0
	 */
	public Transactions getTransactionsPaged(TransactionsFilter transactionsFilter)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getTransactions(transactionsFilter));
	}

	/**
	 * <p>
	 * GET /account/currencies
	 * </p>
	 * 
	 * @since 1.0.0
	 */
	public Currencies getAccountCurrencies()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getAccountCurrencies());
	}

	/**
	 * <p>
	 * GET /rig
	 * </p>
	 * 
	 * @param rigFilter {@link me.gkumaran.miningrigrentals.domain.rig.input.RigFilter}
	 * 
	 * @since 1.0.0
	 */
	public Rigs getRigs(RigFilter rigFilter)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigs(rigFilter));
	}

	/**
	 * <p>
	 * GET /rig/mine
	 * </p>
	 * 
	 * @param type     Algorithm
	 * @param hashrate Calculate and display hashrates
	 * 
	 * @return Filtered Owned Rigs
	 * 
	 * @since 1.0.0
	 */
	public List<Rig> getRigsMine(String type, Boolean hashrate)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigsMine(type, hashrate));
	}

	/**
	 * <p>
	 * GET /rig/mine
	 * </p>
	 * 
	 * @return All Owned Rigs
	 * 
	 * @since 1.0.0
	 */
	public List<Rig> getRigsMine()
	{
		return getRigsMine(null, true);
	}

	/**
	 * <p>
	 * GET /rig
	 * </p>
	 * 
	 * @param rigId Rig ID
	 * 
	 * @since 1.0.0
	 */
	public Rig getRig(Integer rigId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRig(rigId.toString()));
	}

	/**
	 * <p>
	 * GET /rig
	 * </p>
	 * 
	 * @param rigIdList Rig IDs
	 * 
	 * @since 1.0.0
	 */
	public List<Rig> getRigs(Integer... rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigs(parseIdList(rigIdList)));
	}

	/**
	 * <p>
	 * GET /rig
	 * </p>
	 * 
	 * @param rigIdList Rig IDs
	 * 
	 * @since 1.0.0
	 */
	public List<Rig> getRigs(List<Integer> rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigs(parseIdList(rigIdList)));
	}

	/**
	 * <p>
	 * PUT /rig
	 * </p>
	 * 
	 * @param rigs {@link me.gkumaran.miningrigrentals.domain.rig.input.RigConfig}
	 * 
	 * @since 1.0.0
	 */
	public Rig postRigBatch(RigConfig rig)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRig(rig));
	}

	/**
	 * <p>
	 * POST /rig/batch
	 * </p>
	 * 
	 * @param rigs {@link me.gkumaran.miningrigrentals.domain.rig.input.BatchConfig}
	 * @param <T>  {@link me.gkumaran.miningrigrentals.domain.rig.input.RigConfig}
	 * 
	 * @since 1.0.0
	 */
	public List<Rig> postRigBatch(BatchConfig<RigConfig> rigs)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.postRigBatch(rigs));
	}

	/**
	 * <p>
	 * PUT /rig/[ID]/extend
	 * </p>
	 * 
	 * @param extendConfig {@link me.gkumaran.miningrigrentals.domain.rig.input.ExtendConfig}
	 * 
	 * @since 1.0.0
	 */
	public me.gkumaran.miningrigrentals.domain.common.Success putRigExtend(ExtendConfig extendConfig)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRigExtend(extendConfig .getId()
																									.toString(), extendConfig));
	}

	/**
	 * <p>
	 * PUT /rig/[ID1];[ID2];.../extend
	 * </p>
	 * 
	 * @param extendConfig {@link me.gkumaran.miningrigrentals.domain.rig.input.ExtendConfig}
	 * @param rigIdList    Rig IDs
	 * 
	 * @since 1.0.0
	 */
	public List<me.gkumaran.miningrigrentals.domain.common.Success> putRigExtend(ExtendConfig extendConfig, Integer... rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRigsExtend(parseIdList(rigIdList), extendConfig));
	}

	/**
	 * <p>
	 * PUT /rig/[ID1];[ID2];.../extend
	 * </p>
	 * 
	 * @param extendConfig {@link me.gkumaran.miningrigrentals.domain.rig.input.ExtendConfig}
	 * @param rigIdList    Rig IDs
	 * 
	 * @since 1.0.0
	 */
	public List<me.gkumaran.miningrigrentals.domain.common.Success> putRigExtend(ExtendConfig extendConfig, List<Integer> rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRigsExtend(parseIdList(rigIdList), extendConfig));
	}

	/**
	 * <p>
	 * POST /rig/batch/extend
	 * </p>
	 * 
	 * @param rigs {@link me.gkumaran.miningrigrentals.domain.rig.input.BatchConfig}
	 * @param <T>  {@link me.gkumaran.miningrigrentals.domain.rig.input.ExtendConfig}
	 * 
	 * @since 1.0.0
	 */
	public List<me.gkumaran.miningrigrentals.domain.common.Success> postRigExtendBatch(BatchConfig<ExtendConfig> rigs)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.postRigExtendBatch(rigs));
	}

	/**
	 * <p>
	 * GET /rig/[ID]/pool
	 * </p>
	 * 
	 * @param rigId Rig ID
	 * 
	 * @since 1.3.0
	 */
	public RigPool getRigPools(Integer rigId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigPools(rigId.toString()));
	}

	/**
	 * <p>
	 * GET /rig/[ID1];[ID2];.../pool
	 * </p>
	 * 
	 * @param rigIdList List of Rig IDs
	 * 
	 * @since 1.3.0
	 */
	public List<RigPool> getRigPools(Integer... rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigsPools(parseIdList(rigIdList)));
	}

	/**
	 * <p>
	 * GET /rig/[ID1];[ID2];.../pool
	 * </p>
	 * 
	 * @param rigIdList List of Rig IDs
	 * 
	 * @since 1.3.0
	 */
	public List<RigPool> getRigPools(List<Integer> rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigsPools(parseIdList(rigIdList)));
	}

	/**
	 * <p>
	 * PUT /rig/[ID]/pool
	 * </p>
	 * 
	 * @param poolConfig {@link me.gkumaran.miningrigrentals.domain.rig.input.PoolConfig}
	 * @param rigId      Rig ID
	 * 
	 * @since 1.3.0
	 */
	public me.gkumaran.miningrigrentals.domain.common.Success putRigPool(PoolConfig poolConfig, Integer rigId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRigPool(rigId.toString(), poolConfig));
	}

	/**
	 * <p>
	 * PUT /rig/[ID1];[ID2];.../pool
	 * </p>
	 * 
	 * @param poolConfig {@link me.gkumaran.miningrigrentals.domain.rig.input.PoolConfig}
	 * @param rigIdList  List of Rig IDs
	 * 
	 * @since 1.3.0
	 */
	public List<me.gkumaran.miningrigrentals.domain.common.Success> putRigPool(PoolConfig poolConfig, Integer... rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRigsPool(parseIdList(rigIdList), poolConfig));
	}

	/**
	 * <p>
	 * PUT /rig/[ID1];[ID2];.../pool
	 * </p>
	 * 
	 * @param poolConfig {@link me.gkumaran.miningrigrentals.domain.rig.input.PoolConfig}
	 * @param rigIdList  List of Rig IDs
	 * 
	 * @since 1.3.0
	 */
	public List<me.gkumaran.miningrigrentals.domain.common.Success> putRigPool(PoolConfig poolConfig, List<Integer> rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRigsPool(parseIdList(rigIdList), poolConfig));
	}

	/**
	 * <p>
	 * DELETE /rig/[ID]/pool
	 * </p>
	 * 
	 * @param priority Pool number 0-4
	 * @param rigId    Rig ID
	 * 
	 * @since 1.3.0
	 */
	public me.gkumaran.miningrigrentals.domain.common.Success deleteRigPool(Integer priority, Integer rigId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.deleteRigPool(rigId.toString(), priority.toString()));
	}

	/**
	 * <p>
	 * DELETE /rig/[ID1];[ID2];.../pool
	 * </p>
	 * 
	 * @param priority  Pool number 0-4
	 * @param rigIdList List of Rig IDs
	 * 
	 * @since 1.3.0
	 */
	public List<me.gkumaran.miningrigrentals.domain.common.Success> deleteRigPool(Integer priority, Integer... rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.deleteRigsPool(parseIdList(rigIdList), priority.toString()));
	}

	/**
	 * <p>
	 * DELETE /rig/[ID1];[ID2];.../pool
	 * </p>
	 * 
	 * @param priority  Pool number 0-4
	 * @param rigIdList List of Rig IDs
	 * 
	 * @since 1.3.0
	 */
	public List<me.gkumaran.miningrigrentals.domain.common.Success> deleteRigPool(Integer priority, List<Integer> rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.deleteRigsPool(parseIdList(rigIdList), priority.toString()));
	}

	/**
	 * <p>
	 * DELETE /rig/[ID]/port
	 * </p>
	 * 
	 * @param rigId Rig ID
	 * 
	 * @since 1.0.0
	 */
	public Port getPort(Integer rigId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getPort(rigId.toString()));
	}

	/**
	 * <p>
	 * GET /rig/[ID1];[ID2];.../port
	 * </p>
	 * 
	 * @param rigIdList List of Rig IDs
	 * 
	 * @since 1.0.0
	 */
	public List<Port> getPort(Integer... rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getPorts(parseIdList(rigIdList)));
	}

	/**
	 * <p>
	 * GET /rig/[ID1];[ID2];.../port
	 * </p>
	 * 
	 * @param rigIdList List of Rig IDs
	 * 
	 * @since 1.0.0
	 */
	public List<Port> getPort(List<Integer> rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getPorts(parseIdList(rigIdList)));
	}

	/**
	 * <p>
	 * GET /rig/mine/threads
	 * </p>
	 * 
	 * @since 1.0.0
	 */
	public List<Threads> getRigsMineThreads()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigsMineThreads());
	}

	/**
	 * <p>
	 * GET /rig/[ID]/threads
	 * </p>
	 * 
	 * @param rigId Rig ID
	 * 
	 * @since 1.0.0
	 */
	public Threads getRigThreads(Integer rigId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigThreads(rigId.toString()));
	}

	/**
	 * <p>
	 * GET /riggroup
	 * </p>
	 * 
	 * @since 1.0.0
	 */
	public HashMap<Long, RigGroup> getRigGroup()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigGroup());
	}

	/**
	 * <p>
	 * GET /riggroup/[ID]
	 * </p>
	 * 
	 * @param riggroupid Rig Group ID
	 * 
	 * @since 1.0.0
	 */
	public RigGroup getRigGroup(Integer riggroupid)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigGroup(riggroupid.toString()));
	}

	/**
	 * <p>
	 * POST /riggroup/[ID]/add/[rigid1];[rigid2];[rigid3]...
	 * </p>
	 * 
	 * @param riggroupid Rig Group ID
	 * @param rigIdList  List of Rig IDs
	 * 
	 * @since 1.0.0
	 */
	public RigGroup postRigGroupAdd(Integer riggroupid, Integer... rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.postRigGroupAdd(riggroupid.toString(), parseIdList(rigIdList)));
	}

	/**
	 * <p>
	 * POST /riggroup/[ID]/remove/[rigid1];[rigid2];[rigid3]...
	 * </p>
	 * 
	 * @param riggroupid Rig Group ID
	 * @param rigIdList  List of Rig IDs
	 * 
	 * @since 1.0.0
	 */
	public RigGroup postRigGroupRemove(Integer riggroupid, Integer... rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.postRigGroupRemove(riggroupid.toString(), parseIdList(rigIdList)));
	}

	/**
	 * <p>
	 * GET /rental
	 * </p>
	 * 
	 * @param rentalFilter {@link me.gkumaran.miningrigrentals.domain.rental.inputs.RentalFilter}
	 * 
	 * @since 1.0.0
	 */
	public Rentals getRentals(RentalFilter rentalFilter)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRentals(rentalFilter));
	}

	/**
	 * <p>
	 * GET /rental
	 * </p>
	 * 
	 * @param rentalFilter {@link me.gkumaran.miningrigrentals.domain.rental.inputs.RentalFilter}
	 * 
	 * @return All Filtered Rentals without MRR Paging
	 * 
	 * @since 1.0.0
	 */
	public List<Rental> getAllRentals(RentalFilter rentalFilter)
	{
		List<Rental> rentalList = new ArrayList<Rental>();
		Integer total = 0;
		do
		{
			rentalFilter.setLimit(25);
			rentalFilter.setStart(rentalList.size());
			Rentals pagedRentals = getRentals(rentalFilter);
			total = pagedRentals.getTotal();
			rentalList.addAll(pagedRentals.getRentals());
		} while (rentalList.size() < total);
		return rentalList;
	}

	/**
	 * <p>
	 * GET /rental
	 * </p>
	 * 
	 * @return All Purchased Rentals without MRR Paging
	 * 
	 * @since 1.0.0
	 */
	public List<Rental> getRentalsBought()
	{
		return getAllRentals(RentalFilter   .builder()
											.type(TYPE.RENTER)
											.build());
	}

	/**
	 * <p>
	 * GET /rental
	 * </p>
	 * 
	 * @return All Sold Rentals without MRR Paging
	 * 
	 * @since 1.0.0
	 */
	public List<Rental> getRentalsBooked()
	{
		return getAllRentals(RentalFilter   .builder()
											.type(TYPE.OWNER)
											.build());
	}

	/**
	 * <p>
	 * GET /rental
	 * </p>
	 * 
	 * @param rentalId Rental ID
	 * 
	 * @since 1.0.0
	 */
	public Rental getRental(Integer rentalId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRental(rentalId.toString()));
	}

	/**
	 * <p>
	 * GET /rental
	 * </p>
	 * 
	 * @param rentalIdList List of Rental ID
	 * 
	 * @since 1.0.0
	 */
	public List<Rental> getRentals(Integer... rentalIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRentals(parseIdList(rentalIdList)));
	}

	/**
	 * <p>
	 * GET /rental
	 * </p>
	 * 
	 * @param rentalIdList List of Rental ID
	 * 
	 * @since 1.0.0
	 */
	public List<Rental> getRentals(List<Integer> rentalIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRentals(parseIdList(rentalIdList)));
	}

	/**
	 * <p>
	 * GET /rental/[ID]/pool
	 * </p>
	 * 
	 * @param rentalId Rental IDs
	 * 
	 * @since 1.4.0
	 */
	public RigPool getRentalPools(Integer rentalId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRentalPools(rentalId.toString()));
	}

	/**
	 * <p>
	 * GET /rental/[ID1];[ID2];.../pool
	 * </p>
	 * 
	 * @param rentalIdList List of Rental IDs
	 * 
	 * @since 1.4.0
	 */
	public List<RigPool> getRentalPools(Integer... rentalIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRentalsPools(parseIdList(rentalIdList)));
	}

	/**
	 * <p>
	 * GET /rental/[ID1];[ID2];.../pool
	 * </p>
	 * 
	 * @param rentalIdList List of Rental IDs
	 * 
	 * @since 1.4.0
	 */
	public List<RigPool> getRentalPools(List<Integer> rentalIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRentalsPools(parseIdList(rentalIdList)));
	}

	/**
	 * <p>
	 * PUT /rental/[ID]/pool
	 * </p>
	 * 
	 * @param poolConfig {@link me.gkumaran.miningrigrentals.domain.rig.input.PoolConfig}
	 * @param rentalId   Rental ID
	 * 
	 * @since 1.4.0
	 */
	public me.gkumaran.miningrigrentals.domain.common.Success putRentalPool(PoolConfig poolConfig, Integer rentalId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRentalPool(rentalId.toString(), poolConfig));
	}

	/**
	 * <p>
	 * PUT /rental/[ID1];[ID2];.../pool
	 * </p>
	 * 
	 * @param poolConfig   {@link me.gkumaran.miningrigrentals.domain.rig.input.PoolConfig}
	 * @param rentalIdList List of Rental IDs
	 * 
	 * @since 1.4.0
	 */
	public List<me.gkumaran.miningrigrentals.domain.common.Success> putRentalPool(PoolConfig poolConfig, Integer... rentalIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRentalsPool(parseIdList(rentalIdList), poolConfig));
	}

	/**
	 * <p>
	 * PUT /rental/[ID1];[ID2];.../pool
	 * </p>
	 * 
	 * @param poolConfig   {@link me.gkumaran.miningrigrentals.domain.rig.input.PoolConfig}
	 * @param rentalIdList List of Rental IDs
	 * 
	 * @since 1.4.0
	 */
	public List<me.gkumaran.miningrigrentals.domain.common.Success> putRentalPool(PoolConfig poolConfig, List<Integer> rentalIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRentalsPool(parseIdList(rentalIdList), poolConfig));
	}

	/**
	 * <p>
	 * DELETE /rental/[ID]/pool
	 * </p>
	 * 
	 * @param priority Pool no. 0-4
	 * @param rentalId Rental ID
	 * 
	 * @since 1.4.0
	 */
	public me.gkumaran.miningrigrentals.domain.common.Success deleteRentalPool(Integer priority, Integer rentalId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.deleteRentalPool(rentalId.toString(), priority.toString()));
	}

	/**
	 * <p>
	 * DELETE /rental/[ID1];[ID2];.../pool
	 * </p>
	 * 
	 * @param priority     Pool no. 0-4
	 * @param rentalIdList List of Rental IDs
	 * 
	 * @since 1.4.0
	 */
	public List<me.gkumaran.miningrigrentals.domain.common.Success> deleteRentalPool(Integer priority, Integer... rentalIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.deleteRentalsPool(parseIdList(rentalIdList), priority.toString()));
	}

	/**
	 * <p>
	 * DELETE /rental/[ID1];[ID2];.../pool
	 * </p>
	 * 
	 * @param priority     Pool no. 0-4
	 * @param rentalIdList List of Rental IDs
	 * 
	 * @since 1.4.0
	 */
	public List<me.gkumaran.miningrigrentals.domain.common.Success> deleteRentalPool(Integer priority, List<Integer> rentalIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.deleteRentalsPool(parseIdList(rentalIdList), priority.toString()));
	}

	/**
	 * <p>
	 * PUT /rental/[ID]/pool
	 * </p>
	 * 
	 * @param extendConfig {@link me.gkumaran.miningrigrentals.domain.rental.inputs.ExtendConfig}
	 * @param rentalId     Rental ID
	 * 
	 * @since 1.4.0
	 */
	public ExtendResponse putRentalExtend(me.gkumaran.miningrigrentals.domain.rental.inputs.ExtendConfig extendConfig, Integer rentalId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRentalExtend(rentalId.toString(), extendConfig));
	}

	/**
	 * <p>
	 * PUT /rental/[ID1];[ID2];.../pool
	 * </p>
	 * 
	 * @param extendConfig {@link me.gkumaran.miningrigrentals.domain.rental.inputs.ExtendConfig}
	 * @param rentalIdList List of Rental IDs
	 * 
	 * @since 1.4.0
	 */
	public List<ExtendResponse> putRentalsExtend(me.gkumaran.miningrigrentals.domain.rental.inputs.ExtendConfig extendConfig, Integer... rentalIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRentalsExtend(parseIdList(rentalIdList), extendConfig));
	}

	/**
	 * <p>
	 * PUT /rental/[ID1];[ID2];.../pool
	 * </p>
	 * 
	 * @param extendConfig {@link me.gkumaran.miningrigrentals.domain.rental.inputs.ExtendConfig}
	 * @param rentalIdList List of Rental IDs
	 * 
	 * @since 1.4.0
	 */
	public List<ExtendResponse> putRentalsExtend(me.gkumaran.miningrigrentals.domain.rental.inputs.ExtendConfig extendConfig,
			List<Integer> rentalIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRentalsExtend(parseIdList(rentalIdList), extendConfig));
	}

	/**
	 * <p>
	 * GET /rental/[ID]/message
	 * </p>
	 * 
	 * @param rentalId Rental ID
	 * 
	 * @since 1.4.0
	 */
	public Messages getRentalMessage(Integer rentalId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRentalMessage(rentalId.toString()));
	}

	/**
	 * <p>
	 * GET /rental/[ID1];[ID2];.../message
	 * </p>
	 * 
	 * @param rentalIdList List of Rental IDs
	 * 
	 * @since 1.4.0
	 */
	public List<Messages> getRentalMessage(Integer... rentalIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRentalsMessage(parseIdList(rentalIdList)));
	}

	/**
	 * <p>
	 * GET /rental/[ID1];[ID2];.../message
	 * </p>
	 * 
	 * @param rentalIdList List of Rental IDs
	 * 
	 * @since 1.4.0
	 */
	public List<Messages> getRentalMessage(List<Integer> rentalIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRentalsMessage(parseIdList(rentalIdList)));
	}

	/**
	 * <p>
	 * PUT /rental/[ID]/message
	 * </p>
	 * 
	 * @param messageConfig {@link me.gkumaran.miningrigrentals.domain.rental.inputs.MessageConfig}
	 * @param rentalId      Rental ID
	 * 
	 * @since 1.4.0
	 */
	public me.gkumaran.miningrigrentals.domain.common.Success putRentalMessage(MessageConfig messageConfig, Integer rentalId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRentalMessage(rentalId.toString(), messageConfig));
	}

	/**
	 * <p>
	 * PUT /rental/[ID1];[ID2];.../message
	 * </p>
	 * 
	 * @param messageConfig {@link me.gkumaran.miningrigrentals.domain.rental.inputs.MessageConfig}
	 * @param rentalIdList  List of Rental IDs
	 * 
	 * @since 1.4.0
	 */
	public List<me.gkumaran.miningrigrentals.domain.common.Success> putRentalMessage(MessageConfig messageConfig, Integer... rentalIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRentalsMessage(parseIdList(rentalIdList), messageConfig));
	}

	/**
	 * <p>
	 * PUT /rental/[ID1];[ID2];.../message
	 * </p>
	 * 
	 * @param messageConfig {@link me.gkumaran.miningrigrentals.domain.rental.inputs.MessageConfig}
	 * @param rentalIdList  List of Rental IDs
	 * 
	 * @since 1.4.0
	 */
	public List<me.gkumaran.miningrigrentals.domain.common.Success> putRentalMessage(MessageConfig messageConfig, List<Integer> rentalIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRentalsMessage(parseIdList(rentalIdList), messageConfig));
	}

	private String parseIdList(Integer... idList)
	{
		return parseIdList(Arrays.asList(idList));
	}

	private String parseIdList(List<Integer> idList)
	{
		return idList   .stream()
						.map(id -> id.toString())
						.collect(Collectors.joining(";"));
	}
}
