package me.gkumaran.miningrigrentals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.gkumaran.miningrigrentals.domain.WhoAmI;
import me.gkumaran.miningrigrentals.domain.account.Account;
import me.gkumaran.miningrigrentals.domain.account.Balance;
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
import me.gkumaran.miningrigrentals.domain.rig.Rigs;
import me.gkumaran.miningrigrentals.domain.rig.Threads;
import me.gkumaran.miningrigrentals.domain.rig.input.BatchConfig;
import me.gkumaran.miningrigrentals.domain.rig.input.ExtendConfig;
import me.gkumaran.miningrigrentals.domain.rig.input.RigConfig;
import me.gkumaran.miningrigrentals.domain.rig.input.RigFilter;
import me.gkumaran.miningrigrentals.domain.riggroup.RigGroup;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface ApiService
{
	@GET("whoami")
	Call<WhoAmI> WhoAmI();

	@GET("info/servers")
	Call<List<Server>> getServers();

	@GET("info/algos")
	Call<List<Algorithm>> getAlgorithms();

	@GET("info/algos/{name}")
	Call<Algorithm> getAlgorithm(@Path("name")
	String name,
			@Query("currency")
			String Currency);

	@GET("info/currencies")
	Call<Currencies> getCurrencies();

	@GET("account")
	Call<Account> getAccount();

	@GET("account/balance")
	Call<Map<String, Balance>> getBalances();

	@POST("account/transactions")
	Call<Transactions> getTransactions(@Body
	TransactionsFilter transactionsFilter);

	@POST("rig")
	Call<Rigs> getRigs(@Body
	RigFilter rigFilter);

	@POST("rig/mine")
	Call<List<Rig>> getRigsMine(@Query("type")
	String type,
			@Query("hashrate")
			Boolean hashrate);

	@GET("rig/{rigid}")
	Call<Rig> getRig(@Path("rigid")
	String rigId);

	@GET("rig/{rigidlist}")
	Call<List<Rig>> getRigs(@Path("rigidlist")
	String rigidlist);

	@PUT("rig/{rigid}/extend")
	Call<ExtendResult> putRigExtend(@Path("rigid")
	String rigId,
			@Body
			ExtendConfig config);

	@POST("rig/batch/extend")
	Call<List<ExtendResult>> postRigExtendBatch(@Body
	BatchConfig<ExtendConfig> rigs);

	@GET("rig/{rigid}/port")
	Call<Port> getPort(@Path("rigid")
	String rigId);

	@GET("rig/{rigidlist}/port")
	Call<List<Port>> getPorts(@Path("rigidlist")
	String rigidlist);

	@POST("rig/batch")
	Call<List<Rig>> postRigBatch(@Body
	BatchConfig<RigConfig> rigs);

	@GET("rig/mine/threads")
	Call<List<Threads>> getRigsMineThreads();

	@GET("riggroup")
	Call<HashMap<Long, RigGroup>> getRigGroup();

	@GET("riggroup/{riggroupid}")
	Call<RigGroup> getRigGroup(@Path("riggroupid")
	String riggroupid);

	@POST("riggroup/{riggroupid}/add/{rigidlist}")
	Call<RigGroup> postRigGroupAdd(@Path("riggroupid")
	String riggroupid,
			@Path("rigidlist")
			String rigidlist);

	@POST("riggroup/{riggroupid}/remove/{rigidlist}")
	Call<RigGroup> postRigGroupRemove(@Path("riggroupid")
	String riggroupid,
			@Path("rigidlist")
			String rigidlist);

	@POST("rental")
	Call<Rentals> getRentals(@Body
	RentalFilter rentalFilter);

	@GET("rental/{rentalid}")
	Call<Rental> getRental(@Path("rentalid")
	String rentalid);

	@GET("rental/{rentalidlist}")
	Call<List<Rental>> getRentals(@Path("rentalidlist")
	String rentalidlist);
}
