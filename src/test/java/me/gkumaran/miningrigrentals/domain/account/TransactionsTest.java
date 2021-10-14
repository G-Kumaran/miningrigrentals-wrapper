package me.gkumaran.miningrigrentals.domain.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import me.gkumaran.miningrigrentals.ApiClientTest;
import me.gkumaran.miningrigrentals.domain.account.inputs.TransactionsFilter;

@Slf4j
class TransactionsTest extends ApiClientTest
{
	@Test
	void getTransactionsRentalTest()
	{
		final lombok.val response = miningRigRentals.getTransactions(TransactionsFilter .builder()
																						.rental(3200179)
																						.build());
		log.info("{}", response);
		assertThat(response).asList()
							.first()
							.isInstanceOf(Transaction.class);

	}

	@Test
	void getTransactionsTimeTest()
	{
		final lombok.val response = miningRigRentals.getTransactions(TransactionsFilter .builder()
																						.time_greater_eq(1633616236L)
																						.build());
		log.info("{}", response);
		assertThat(response).asList()
							.first()
							.isInstanceOf(Transaction.class);

	}

	@Test
	void getTransactionsTest()
	{
		final lombok.val response = miningRigRentals.getTransactions();
		log.info("{}", response);
		assertThat(response).asList()
							.hasSizeGreaterThan(120)
							.first()
							.isInstanceOf(Transaction.class);

	}

}
