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
import me.gkumaran.miningrigrentals.domain.rental.Rental;
import me.gkumaran.miningrigrentals.domain.rental.Rentals;
import me.gkumaran.miningrigrentals.domain.rental.inputs.RentalFilter;
import me.gkumaran.miningrigrentals.domain.rig.ExtendResult;
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

	public WhoAmI WhoAmI()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.WhoAmI());
	}

	public List<Server> getServers()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getServers());
	}

	public List<Algorithm> getAlgorithms()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getAlgorithms());
	}

	public Algorithm getAlgorithm(String algorithm, String currency)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getAlgorithm(algorithm, currency));
	}

	public Algorithm getAlgorithm(String algorithm)
	{
		return getAlgorithm(algorithm, null);
	}

	public Currencies getCurrencies()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getCurrencies());
	}

	public Account getAccount()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getAccount());
	}

	public Map<String, Balance> getBalances()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getBalances());
	}

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

	public List<Transaction> getTransactions()
	{
		return getTransactions(TransactionsFilter   .builder()
													.build());
	}

	public Transactions getTransactionsPaged(TransactionsFilter transactionsFilter)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getTransactions(transactionsFilter));
	}

	public Rigs getRigs(RigFilter rigFilter)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigs(rigFilter));
	}

	public List<Rig> getRigsMine(String type, Boolean hashrate)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigsMine(type, hashrate));
	}

	public List<Rig> getRigsMine()
	{
		return getRigsMine(null, true);
	}

	public Rig getRig(Integer rigId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRig(rigId.toString()));
	}

	public List<Rig> getRigs(Integer... rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigs(Arrays.asList(rigIdList)
																						.stream()
																						.map(rigId -> rigId.toString())
																						.collect(Collectors.joining(";"))));
	}

	public List<Rig> postRigBatch(BatchConfig<RigConfig> rigs)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.postRigBatch(rigs));
	}

	public ExtendResult putRigExtend(ExtendConfig extendConfig)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRigExtend(extendConfig .getId()
																									.toString(), extendConfig));
	}

	public List<ExtendResult> postRigExtendBatch(BatchConfig<ExtendConfig> rigs)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.postRigExtendBatch(rigs));
	}

	public RigPool getRigPools(Integer rigId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigPools(rigId.toString()));
	}

	public List<RigPool> getRigPools(Integer... rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigsPools(Arrays   .asList(rigIdList)
																								.stream()
																								.map(rigId -> rigId.toString())
																								.collect(Collectors.joining(";"))));
	}

	public me.gkumaran.miningrigrentals.domain.common.Success putRigPool(Integer rigId, PoolConfig poolConfig)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.putRigPool(rigId.toString(), poolConfig));
	}

	public me.gkumaran.miningrigrentals.domain.common.Success deleteRigPool(Integer rigId, Integer priority)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.deleteRigPool(rigId.toString(), priority.toString()));
	}

	public Port getPort(Integer rigId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getPort(rigId.toString()));
	}

	public List<Port> getPort(Integer... rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getPorts(Arrays   .asList(rigIdList)
																							.stream()
																							.map(rigId -> rigId.toString())
																							.collect(Collectors.joining(";"))));
	}

	public List<Threads> getRigsMineThreads()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigsMineThreads());
	}

	public HashMap<Long, RigGroup> getRigGroup()
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigGroup());
	}

	public RigGroup getRigGroup(Integer riggroupid)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRigGroup(riggroupid.toString()));
	}

	public RigGroup postRigGroupAdd(Integer riggroupid, Integer... rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.postRigGroupAdd(riggroupid.toString(), Arrays .asList(rigIdList)
																														.stream()
																														.map(rigId -> rigId.toString())
																														.collect(Collectors.joining(";"))));
	}

	public RigGroup postRigGroupRemove(Integer riggroupid, Integer... rigIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.postRigGroupRemove(riggroupid.toString(), Arrays  .asList(rigIdList)
																															.stream()
																															.map(rigId -> rigId.toString())
																															.collect(Collectors.joining(";"))));
	}

	public Rentals getRentals(RentalFilter rentalFilter)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRentals(rentalFilter));
	}

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

	public List<Rental> getRentalsBought()
	{
		return getAllRentals(RentalFilter   .builder()
											.type(TYPE.RENTER)
											.build());
	}

	public List<Rental> getRentalsBooked()
	{
		return getAllRentals(RentalFilter   .builder()
											.type(TYPE.OWNER)
											.build());
	}

	public Rental getRental(Integer rentalId)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRental(rentalId.toString()));
	}

	public List<Rental> getRentals(List<Integer> rentalIdList)
	{
		return miningRigRentalsApi.executeSync(miningRigRentalsApiService.getRentals(rentalIdList   .stream()
																									.map(rentalId -> rentalId.toString())
																									.collect(Collectors.joining(";"))));
	}
}
