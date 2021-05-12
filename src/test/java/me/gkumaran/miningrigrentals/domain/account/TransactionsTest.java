package me.gkumaran.miningrigrentals.domain.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import lombok.var;
import lombok.extern.slf4j.Slf4j;
import me.gkumaran.miningrigrentals.ApiClientTest;
import me.gkumaran.miningrigrentals.domain.account.inputs.TransactionsFilter;

@Slf4j
class TransactionsTest extends ApiClientTest
{

	@Test
	void getTransactionsRentalTest()
	{
		final var response = miningRigRentals.getTransactions(TransactionsFilter.builder()
																				.rental(3200179)
																				.build());
		log.info("{}", response);
		assertThat(response).isInstanceOf(Transactions.class)
							.extracting("transactions")
							.asList()
							.first()
							.isInstanceOf(Transaction.class);

	}

	@Test
	void getTransactionsReferralTest()
	{
		final var response = miningRigRentals.getTransactions(TransactionsFilter.builder()
																				.type(TYPE.REFERRAL)
																				.build());
		log.info("{}", response);
		assertThat(response).isInstanceOf(Transactions.class)
							.extracting("transactions")
							.asList()
							.first()
							.isInstanceOf(Transaction.class)
							.hasFieldOrPropertyWithValue("type", TYPE.REFERRAL);

	}

	@Test
	void getTransactionsTest()
	{
		final var response = miningRigRentals.getTransactions(TransactionsFilter.builder()
																				.build());
		log.info("{}", response);
		assertThat(response).isInstanceOf(Transactions.class)
							.extracting("transactions")
							.asList()
							.first()
							.isInstanceOf(Transaction.class);

	}

}
