package me.gkumaran.miningrigrentals;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import lombok.extern.slf4j.Slf4j;
import me.gkumaran.miningrigrentals.constant.TYPE;
import me.gkumaran.miningrigrentals.domain.account.inputs.TransactionsFilter;
import me.gkumaran.miningrigrentals.domain.rental.inputs.RentalFilter;
import me.gkumaran.miningrigrentals.domain.rig.input.PoolConfig;
import me.gkumaran.miningrigrentals.domain.rig.input.RigFilter;

@Slf4j
class ApiClientTest extends ApiTest
{
	private <T> T testAndPrint(ThrowingSupplier<T> methodCall)
	{
		lombok.val response = Assertions.assertDoesNotThrow(methodCall);
		log.info("Response : {}", response);
		return response;
	}

	@Test
	final void testWhoAmI()
	{
		this.testAndPrint(() -> miningRigRentals.WhoAmI());
	}

	@Test
	final void testGetServers()
	{
		this.testAndPrint(() -> miningRigRentals.getServers());
	}

	@Test
	final void testGetAlgorithms()
	{
		this.testAndPrint(() -> miningRigRentals.getAlgorithms());
	}

	@Test
	final void testGetAlgorithmByCurrency()
	{
		this.testAndPrint(() -> miningRigRentals.getAlgorithm("sha256", "ETH"));
	}

	@Test
	final void testGetAlgorithm()
	{
		this.testAndPrint(() -> miningRigRentals.getAlgorithm("sha256"));
	}

	@Test
	final void testGetCurrencies()
	{
		this.testAndPrint(() -> miningRigRentals.getCurrencies());
	}

	@Test
	final void testGetPricing()
	{
		this.testAndPrint(() -> miningRigRentals.getPricing());
	}

	@Test
	final void testGetAccount()
	{
		this.testAndPrint(() -> miningRigRentals.getAccount());
	}

	@Test
	final void testGetBalances()
	{
		this.testAndPrint(() -> miningRigRentals.getBalances());
	}

	@Test
	final void testGetTransactionsTransactionsFilter()
	{
		this.testAndPrint(() -> miningRigRentals.getTransactions(TransactionsFilter .builder()
																					.rental(rentalId)
																					.build()));
	}

	@Test
	@Disabled("Will Get ALL Transactions")
	final void testGetTransactions()
	{
		this.testAndPrint(() -> miningRigRentals.getTransactions());
	}

	@Test
	final void testGetTransactionsPaged()
	{
		this.testAndPrint(() -> miningRigRentals.getTransactionsPaged(TransactionsFilter.builder()
																						.algo("sha256")
																						.build()));
		this.testAndPrint(() -> miningRigRentals.getTransactionsPaged(TransactionsFilter.builder()
																						.time_greater_eq(Instant.now()
																												.minus(12, ChronoUnit.HOURS)
																												.getEpochSecond())
																						.build()));
		this.testAndPrint(() -> miningRigRentals.getTransactionsPaged(TransactionsFilter.builder()
																						.build()));
	}

	@Test
	final void testGetAccountCurrencies()
	{
		this.testAndPrint(() -> miningRigRentals.getAccountCurrencies());
	}

	@Test
	final void testGetRigsRigFilter()
	{
		this.testAndPrint(() -> miningRigRentals.getRigs(RigFilter  .builder()
																	.type("scrypt")
																	.build()));
	}

	@Test
	final void testGetRigsMineStringBoolean()
	{
		this.testAndPrint(() -> miningRigRentals.getRigsMine("scrypt", true));
	}

	@Test
	final void testGetRigsMine()
	{
		this.testAndPrint(() -> miningRigRentals.getRigsMine());
	}

	@Test
	final void testGetRig()
	{
		this.testAndPrint(() -> miningRigRentals.getRig(rigId));
	}

	@Test
	final void testGetRigsIntegerArray()
	{
		this.testAndPrint(() -> miningRigRentals.getRigs(rigId, rigId));
	}

	@Test
	final void testGetRigsListOfInteger()
	{
		this.testAndPrint(() -> miningRigRentals.getRigs(List.of(rigId, rigId)));
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPostRigBatchRigConfig()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPostRigBatchBatchConfigOfRigConfig()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPutRigExtendExtendConfig()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPutRigExtendExtendConfigIntegerArray()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPutRigExtendExtendConfigListOfInteger()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPostRigExtendBatch()
	{
		fail("Not yet implemented");
	}

	@Test
	final void testGetRigPoolsInteger()
	{
		this.testAndPrint(() -> miningRigRentals.getRigPools(rigId));
	}

	@Test
	final void testGetRigPoolsIntegerArray()
	{
		this.testAndPrint(() -> miningRigRentals.getRigPools(rigId, rigId));
	}

	@Test
	final void testGetRigPoolsListOfInteger()
	{
		this.testAndPrint(() -> miningRigRentals.getRigPools(List.of(rigId, rigId)));
	}

	@Test
	@Order(Integer.MIN_VALUE)
	final void testPutRigPoolPoolConfigInteger()
	{
		lombok.val response = this.testAndPrint(() -> miningRigRentals.putRigPool(PoolConfig.builder()
																							.priority(1)
																							.host("eu-de02.miningrigrentals.com")
																							.port(3333)
																							.user("testing")
																							.pass("x")
																							.build(), rigId));
		Assertions.assertTrue(response.getSuccess());
	}

	@Test
	@Order(Integer.MIN_VALUE)
	final void testPutRigPoolPoolConfigIntegerArray()
	{
		lombok.val response = this.testAndPrint(() -> miningRigRentals.putRigPool(PoolConfig.builder()
																							.priority(1)
																							.host("eu-de02.miningrigrentals.com")
																							.port(3333)
																							.user("testing")
																							.pass("x")
																							.build(), rigId, rigId));
		assertThat(response).allMatch(me.gkumaran.miningrigrentals.domain.common.Success::getSuccess);
	}

	@Test
	@Order(Integer.MIN_VALUE)
	final void testPutRigPoolPoolConfigListOfInteger()
	{
		lombok.val response = this.testAndPrint(() -> miningRigRentals.putRigPool(PoolConfig.builder()
																							.priority(1)
																							.host("eu-de02.miningrigrentals.com")
																							.port(3333)
																							.user("testing")
																							.pass("x")
																							.build(), List.of(rigId, rigId)));
		assertThat(response).allMatch(me.gkumaran.miningrigrentals.domain.common.Success::getSuccess);
	}

	@Test
	@Order(Integer.MAX_VALUE)
	final void testDeleteRigPoolIntegerInteger()
	{
		lombok.val response = this.testAndPrint(() -> miningRigRentals.deleteRigPool(1, rigId));
		Assertions.assertTrue(response.getSuccess());
	}

	@Test
	@Disabled("Tested via individual call")
	final void testDeleteRigPoolIntegerIntegerArray()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("Tested via individual call")
	final void testDeleteRigPoolIntegerListOfInteger()
	{
		fail("Not yet implemented");
	}

	@Test
	final void testGetPortInteger()
	{
		this.testAndPrint(() -> miningRigRentals.getPort(rigId));
	}

	@Test
	final void testGetPortIntegerArray()
	{
		this.testAndPrint(() -> miningRigRentals.getPort(rigId, rigId));
	}

	@Test
	final void testGetPortListOfInteger()
	{
		this.testAndPrint(() -> miningRigRentals.getPort(List.of(rigId, rigId)));
	}

	@Test
	final void testGetRigsMineThreads()
	{
		this.testAndPrint(() -> miningRigRentals.getRigsMineThreads());
	}

	@Test
	final void testGetRigThreads()
	{
		this.testAndPrint(() -> miningRigRentals.getRigThreads(rigId));
	}

	@Test
	final void testGetRigGroup()
	{
		this.testAndPrint(() -> miningRigRentals.getRigGroup());
	}

	@Test
	final void testGetRigGroupInteger()
	{
		this.testAndPrint(() -> miningRigRentals.getRigGroup(4));
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPostRigGroupAdd()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPostRigGroupRemove()
	{
		fail("Not yet implemented");
	}

	@Test
	final void testGetRentalsRentalFilter()
	{
		this.testAndPrint(() -> miningRigRentals.getRentals(RentalFilter.builder()
																		.algo("lyra2z")
																		.type(TYPE.OWNER)
																		.build()));
		this.testAndPrint(() -> miningRigRentals.getRentals(RentalFilter.builder()
																		.algo("lyra2z")
																		.type(TYPE.RENTER)
																		.build()));
	}

	@Test
	@Disabled("Large Dataset")
	final void testGetAllRentals()
	{
		this.testAndPrint(() -> miningRigRentals.getAllRentals(RentalFilter .builder()
																			.algo("lyra2z")
																			.build()));
	}

	@Test
	@Disabled("Large Dataset")
	final void testGetRentalsBought()
	{
		this.testAndPrint(() -> miningRigRentals.getRentalsBought());
	}

	@Test
	@Disabled("Large Dataset")
	final void testGetRentalsBooked()
	{
		this.testAndPrint(() -> miningRigRentals.getRentalsBooked());
	}

	@Test
	final void testGetRental()
	{
		this.testAndPrint(() -> miningRigRentals.getRental(rentalId));
	}

	@Test
	final void testGetRentalsIntegerArray()
	{
		this.testAndPrint(() -> miningRigRentals.getRentals(rentalId, rentalId));
	}

	@Test
	final void testGetRentalsListOfInteger()
	{
		this.testAndPrint(() -> miningRigRentals.getRentals(List.of(rentalId, rentalId)));
	}

	@Test
	final void testGetRentalPoolsInteger()
	{
		this.testAndPrint(() -> miningRigRentals.getRentalPools(rentalIdBuy));
	}

	@Test
	final void testGetRentalPoolsIntegerArray()
	{
		this.testAndPrint(() -> miningRigRentals.getRentalPools(rentalIdBuy, rentalIdBuy));
	}

	@Test
	final void testGetRentalPoolsListOfInteger()
	{
		this.testAndPrint(() -> miningRigRentals.getRentalPools(List.of(rentalIdBuy, rentalIdBuy)));
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPutRentalPoolPoolConfigInteger()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPutRentalPoolPoolConfigIntegerArray()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPutRentalPoolPoolConfigListOfInteger()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testDeleteRentalPoolIntegerInteger()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testDeleteRentalPoolIntegerIntegerArray()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testDeleteRentalPoolIntegerListOfInteger()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPutRentalExtend()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPutRentalsExtendExtendConfigIntegerArray()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPutRentalsExtendExtendConfigListOfInteger()
	{
		fail("Not yet implemented");
	}

	@Test
	final void testGetRentalMessageInteger()
	{
		this.testAndPrint(() -> miningRigRentals.getRentalMessage(rentalId));
	}

	@Test
	final void testGetRentalMessageIntegerArray()
	{
		this.testAndPrint(() -> miningRigRentals.getRentalMessage(rentalId, rentalId));
	}

	@Test
	final void testGetRentalMessageListOfInteger()
	{
		this.testAndPrint(() -> miningRigRentals.getRentalMessage(List.of(rentalId, rentalId)));
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPutRentalMessageMessageConfigInteger()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPutRentalMessageMessageConfigIntegerArray()
	{
		fail("Not yet implemented");
	}

	@Test
	@Disabled("MRR validation of inputs is relaxed")
	final void testPutRentalMessageMessageConfigListOfInteger()
	{
		fail("Not yet implemented");
	}
}
