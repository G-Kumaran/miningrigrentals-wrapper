package me.gkumaran.miningrigrentals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import me.gkumaran.miningrigrentals.domain.WhoAmI;
import me.gkumaran.miningrigrentals.domain.account.Account;
import me.gkumaran.miningrigrentals.domain.account.Balance;
import me.gkumaran.miningrigrentals.domain.account.Transactions;
import me.gkumaran.miningrigrentals.domain.account.inputs.TransactionsFilter;
import me.gkumaran.miningrigrentals.domain.info.Algorithm;
import me.gkumaran.miningrigrentals.domain.info.Currencies;
import me.gkumaran.miningrigrentals.domain.info.Pricing;
import me.gkumaran.miningrigrentals.domain.info.Server;
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

@HttpExchange
interface ApiService
{
	// INFORMATION END-POINTS
	@GetExchange("whoami")
	WhoAmI WhoAmI();

	@GetExchange("info/servers")
	List<Server> getServers();

	@GetExchange("info/algos")
	List<Algorithm> getAlgorithms();

	@GetExchange("info/algos/{name}")
	Algorithm getAlgorithm(@PathVariable("name") String name, @RequestParam(value = "currency",
																			required = false) String Currency);

	@GetExchange("info/currencies")
	Currencies getCurrencies();

	// PRICING END-POINTS
	@GetExchange("pricing")
	Pricing getPricing();

	// ACCOUNT END-POINTS
	@GetExchange("account")
	Account getAccount();

	@GetExchange("account/balance")
	Map<String, Balance> getBalances();

	@PostExchange("account/transactions")
	Transactions getTransactions(@RequestBody TransactionsFilter transactionsFilter);

	@GetExchange("account/currencies")
	Currencies getAccountCurrencies();

	// RIG END-POINTS
	@PostExchange("rig")
	Rigs getRigs(@RequestBody RigFilter rigFilter);

	@PostExchange("rig/mine")
	List<Rig> getRigsMine(@RequestParam(value = "type",
										required = false) String type,
			@RequestParam(  value = "hashrate",
							required = false) Boolean hashrate);

	@GetExchange("rig/{rigid}")
	Rig getRig(@PathVariable("rigid") String rigId);

	@GetExchange("rig/{rigidlist}")
	List<Rig> getRigs(@PathVariable("rigidlist") String rigidlist);

	@PutExchange("rig")
	Rig putRig(@RequestBody RigConfig rig);

	@PostExchange("rig/batch")
	List<Rig> postRigBatch(@RequestBody BatchConfig<RigConfig> rigs);

	@PutExchange("rig/{rigid}/extend")
	me.gkumaran.miningrigrentals.domain.common.Success putRigExtend(@PathVariable("rigid") String rigId, @RequestBody ExtendConfig config);

	@PutExchange("rig/{rigid}/extend")
	List<me.gkumaran.miningrigrentals.domain.common.Success> putRigsExtend(@PathVariable("rigidlist") String rigidlist,
			@RequestBody ExtendConfig config);

	@PostExchange("rig/batch/extend")
	List<me.gkumaran.miningrigrentals.domain.common.Success> postRigExtendBatch(@RequestBody BatchConfig<ExtendConfig> rigs);

	@GetExchange("rig/{rigid}/pool")
	RigPool getRigPools(@PathVariable("rigid") String rigId);

	@GetExchange("rig/{rigidlist}/pool")
	List<RigPool> getRigsPools(@PathVariable("rigidlist") String rigidlist);

	@PutExchange("rig/{rigid}/pool")
	me.gkumaran.miningrigrentals.domain.common.Success putRigPool(@PathVariable("rigid") String rigId, @RequestBody PoolConfig config);

	@PutExchange("rig/{rigidlist}/pool")
	List<me.gkumaran.miningrigrentals.domain.common.Success> putRigsPool(@PathVariable("rigidlist") String rigId, @RequestBody PoolConfig config);

	@DeleteExchange("rig/{rigid}/pool/{priority}")
	me.gkumaran.miningrigrentals.domain.common.Success deleteRigPool(@PathVariable("rigid") String rigId, @PathVariable("priority") String priority);

	@DeleteExchange("rig/{rigidlist}/pool/{priority}")
	List<me.gkumaran.miningrigrentals.domain.common.Success> deleteRigsPool(@PathVariable("rigidlist") String rigId,
			@PathVariable("priority") String priority);

	@GetExchange("rig/{rigid}/port")
	Port getPort(@PathVariable("rigid") String rigId);

	@GetExchange("rig/{rigidlist}/port")
	List<Port> getPorts(@PathVariable("rigidlist") String rigidlist);

	@GetExchange("rig/{rigid}/threads")
	List<Threads> getRigThreads(@PathVariable("rigid") String rigId);

	@GetExchange("rig/mine/threads")
	List<Threads> getRigsMineThreads();

	// RIG GROUP END-POINTS
	@GetExchange("riggroup")
	HashMap<Long, RigGroup> getRigGroup();

	@GetExchange("riggroup/{riggroupid}")
	RigGroup getRigGroup(@PathVariable("riggroupid") String riggroupid);

	@PostExchange("riggroup/{riggroupid}/add/{rigidlist}")
	RigGroup postRigGroupAdd(@PathVariable("riggroupid") String riggroupid, @PathVariable("rigidlist") String rigidlist);

	@PostExchange("riggroup/{riggroupid}/remove/{rigidlist}")
	RigGroup postRigGroupRemove(@PathVariable("riggroupid") String riggroupid, @PathVariable("rigidlist") String rigidlist);

	// RENTAL END-POINTS
	@PostExchange("rental")
	Rentals getRentals(@RequestBody RentalFilter rentalFilter);

	@GetExchange("rental/{rentalid}")
	Rental getRental(@PathVariable("rentalid") String rentalid);

	@GetExchange("rental/{rentalidlist}")
	List<Rental> getRentals(@PathVariable("rentalidlist") String rentalidlist);

	@PutExchange("rental/{rentalid}/extend")
	me.gkumaran.miningrigrentals.domain.rental.ExtendResponse putRentalExtend(@PathVariable("rentalid") String rentalid,
			@RequestBody me.gkumaran.miningrigrentals.domain.rental.inputs.ExtendConfig config);

	@PutExchange("rental/{rentalidlist}/extend")
	List<me.gkumaran.miningrigrentals.domain.rental.ExtendResponse> putRentalsExtend(@PathVariable("rentalidlist") String rentalidlist,
			@RequestBody me.gkumaran.miningrigrentals.domain.rental.inputs.ExtendConfig config);

	@GetExchange("rental/{rentalid}/pool")
	RigPool getRentalPools(@PathVariable("rentalid") String rentalid);

	@GetExchange("rental/{rentalidlist}/pool")
	List<RigPool> getRentalsPools(@PathVariable("rentalidlist") String rentalidlist);

	@PutExchange("rental/{rentalid}/pool")
	me.gkumaran.miningrigrentals.domain.common.Success putRentalPool(@PathVariable("rentalid") String rentalid, @RequestBody PoolConfig config);

	@PutExchange("rental/{rentalidlist}/pool")
	List<me.gkumaran.miningrigrentals.domain.common.Success> putRentalsPool(@PathVariable("rentalidlist") String rentalidlist,
			@RequestBody PoolConfig config);

	@DeleteExchange("rental/{rentalid}/pool/{priority}")
	me.gkumaran.miningrigrentals.domain.common.Success deleteRentalPool(@PathVariable("rentalid") String rentalid,
			@PathVariable("priority") String priority);

	@DeleteExchange("rental/{rentalidlist}/pool/{priority}")
	List<me.gkumaran.miningrigrentals.domain.common.Success> deleteRentalsPool(@PathVariable("rentalidlist") String rentalidlist,
			@PathVariable("priority") String priority);

	// MESSAGE END-POINTS
	@GetExchange("rental/{rentalid}/message")
	Messages getRentalMessage(@PathVariable("rentalid") String rentalid);

	@GetExchange("rental/{rentalidlist}/message")
	List<Messages> getRentalsMessage(@PathVariable("rentalidlist") String rentalidlist);

	@PutExchange("rental/{rentalid}/message")
	List<me.gkumaran.miningrigrentals.domain.common.Success> putRentalMessage(@PathVariable("rentalid") String rentalid,
			@RequestBody MessageConfig config);

	@PutExchange("rental/{rentalidlist}/message")
	List<me.gkumaran.miningrigrentals.domain.common.Success> putRentalsMessage(@PathVariable("rentalidlist") String rentalidlist,
			@RequestBody MessageConfig config);
}
