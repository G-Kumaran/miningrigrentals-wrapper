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
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface ApiService
{
	// INFORMATION END-POINTS
	@GET("whoami")
	Call<WhoAmI> WhoAmI();

	@GET("info/servers")
	Call<List<Server>> getServers();

	@GET("info/algos")
	Call<List<Algorithm>> getAlgorithms();

	@GET("info/algos/{name}")
	Call<Algorithm> getAlgorithm(@Path("name") String name, @Query("currency") String Currency);

	@GET("info/currencies")
	Call<Currencies> getCurrencies();

	// PRICING END-POINTS
	@GET("pricing")
	Call<Pricing> getPricing();

	// ACCOUNT END-POINTS
	@GET("account")
	Call<Account> getAccount();

	@GET("account/balance")
	Call<Map<String, Balance>> getBalances();

	@POST("account/transactions")
	Call<Transactions> getTransactions(@Body TransactionsFilter transactionsFilter);

	@GET("account/currencies")
	Call<Currencies> getAccountCurrencies();

	// RIG END-POINTS
	@POST("rig")
	Call<Rigs> getRigs(@Body RigFilter rigFilter);

	@POST("rig/mine")
	Call<List<Rig>> getRigsMine(@Query("type") String type, @Query("hashrate") Boolean hashrate);

	@GET("rig/{rigid}")
	Call<Rig> getRig(@Path("rigid") String rigId);

	@GET("rig/{rigidlist}")
	Call<List<Rig>> getRigs(@Path("rigidlist") String rigidlist);

	@PUT("rig")
	Call<Rig> putRig(@Body RigConfig rig);

	@POST("rig/batch")
	Call<List<Rig>> postRigBatch(@Body BatchConfig<RigConfig> rigs);

	@PUT("rig/{rigid}/extend")
	Call<me.gkumaran.miningrigrentals.domain.common.Success> putRigExtend(@Path("rigid") String rigId, @Body ExtendConfig config);

	@PUT("rig/{rigid}/extend")
	Call<List<me.gkumaran.miningrigrentals.domain.common.Success>> putRigsExtend(@Path("rigidlist") String rigidlist, @Body ExtendConfig config);

	@POST("rig/batch/extend")
	Call<List<me.gkumaran.miningrigrentals.domain.common.Success>> postRigExtendBatch(@Body BatchConfig<ExtendConfig> rigs);

	@GET("rig/{rigid}/pool")
	Call<RigPool> getRigPools(@Path("rigid") String rigId);

	@GET("rig/{rigidlist}/pool")
	Call<List<RigPool>> getRigsPools(@Path("rigidlist") String rigidlist);

	@PUT("rig/{rigid}/pool")
	Call<me.gkumaran.miningrigrentals.domain.common.Success> putRigPool(@Path("rigid") String rigId, @Body PoolConfig config);

	@PUT("rig/{rigidlist}/pool")
	Call<List<me.gkumaran.miningrigrentals.domain.common.Success>> putRigsPool(@Path("rigidlist") String rigId, @Body PoolConfig config);

	@DELETE("rig/{rigid}/pool/{priority}")
	Call<me.gkumaran.miningrigrentals.domain.common.Success> deleteRigPool(@Path("rigid") String rigId, @Path("priority") String priority);

	@DELETE("rig/{rigidlist}/pool/{priority}")
	Call<List<me.gkumaran.miningrigrentals.domain.common.Success>> deleteRigsPool(@Path("rigidlist") String rigId, @Path("priority") String priority);

	@GET("rig/{rigid}/port")
	Call<Port> getPort(@Path("rigid") String rigId);

	@GET("rig/{rigidlist}/port")
	Call<List<Port>> getPorts(@Path("rigidlist") String rigidlist);

	@GET("rig/{rigid}/threads")
	Call<List<Threads>> getRigThreads(@Path("rigid") String rigId);

	@GET("rig/mine/threads")
	Call<List<Threads>> getRigsMineThreads();

	// RIG GROUP END-POINTS
	@GET("riggroup")
	Call<HashMap<Long, RigGroup>> getRigGroup();

	@GET("riggroup/{riggroupid}")
	Call<RigGroup> getRigGroup(@Path("riggroupid") String riggroupid);

	@POST("riggroup/{riggroupid}/add/{rigidlist}")
	Call<RigGroup> postRigGroupAdd(@Path("riggroupid") String riggroupid, @Path("rigidlist") String rigidlist);

	@POST("riggroup/{riggroupid}/remove/{rigidlist}")
	Call<RigGroup> postRigGroupRemove(@Path("riggroupid") String riggroupid, @Path("rigidlist") String rigidlist);

	// RENTAL END-POINTS
	@POST("rental")
	Call<Rentals> getRentals(@Body RentalFilter rentalFilter);

	@GET("rental/{rentalid}")
	Call<Rental> getRental(@Path("rentalid") String rentalid);

	@GET("rental/{rentalidlist}")
	Call<List<Rental>> getRentals(@Path("rentalidlist") String rentalidlist);

	@PUT("rental/{rentalid}/extend")
	Call<me.gkumaran.miningrigrentals.domain.rental.ExtendResponse> putRentalExtend(@Path("rentalid") String rentalid,
			@Body me.gkumaran.miningrigrentals.domain.rental.inputs.ExtendConfig config);

	@PUT("rental/{rentalidlist}/extend")
	Call<List<me.gkumaran.miningrigrentals.domain.rental.ExtendResponse>> putRentalsExtend(@Path("rentalidlist") String rentalidlist,
			@Body me.gkumaran.miningrigrentals.domain.rental.inputs.ExtendConfig config);

	@GET("rental/{rentalid}/pool")
	Call<RigPool> getRentalPools(@Path("rentalid") String rentalid);

	@GET("rental/{rentalidlist}/pool")
	Call<List<RigPool>> getRentalsPools(@Path("rentalidlist") String rentalidlist);

	@PUT("rental/{rentalid}/pool")
	Call<me.gkumaran.miningrigrentals.domain.common.Success> putRentalPool(@Path("rentalid") String rentalid, @Body PoolConfig config);

	@PUT("rental/{rentalidlist}/pool")
	Call<List<me.gkumaran.miningrigrentals.domain.common.Success>> putRentalsPool(@Path("rentalidlist") String rentalidlist, @Body PoolConfig config);

	@DELETE("rental/{rentalid}/pool/{priority}")
	Call<me.gkumaran.miningrigrentals.domain.common.Success> deleteRentalPool(@Path("rentalid") String rentalid, @Path("priority") String priority);

	@DELETE("rental/{rentalidlist}/pool/{priority}")
	Call<List<me.gkumaran.miningrigrentals.domain.common.Success>> deleteRentalsPool(@Path("rentalidlist") String rentalidlist,
			@Path("priority") String priority);

	// MESSAGE END-POINTS
	@GET("rental/{rentalid}/message")
	Call<Messages> getRentalMessage(@Path("rentalid") String rentalid);

	@GET("rental/{rentalidlist}/message")
	Call<List<Messages>> getRentalsMessage(@Path("rentalidlist") String rentalidlist);

	@PUT("rental/{rentalid}/message")
	Call<me.gkumaran.miningrigrentals.domain.common.Success> putRentalMessage(@Path("rentalid") String rentalid, @Body MessageConfig config);

	@PUT("rental/{rentalidlist}/message")
	Call<List<me.gkumaran.miningrigrentals.domain.common.Success>> putRentalsMessage(@Path("rentalidlist") String rentalidlist,
			@Body MessageConfig config);
}
