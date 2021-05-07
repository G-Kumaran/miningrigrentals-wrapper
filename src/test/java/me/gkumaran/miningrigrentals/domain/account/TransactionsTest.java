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
	void getTransactionsTest()
	{
		log.info("{}", miningRigRentals.getTransactions(TransactionsFilter  .builder()
																			.rental(3200179)
																			.build()));
		assertThat(miningRigRentals.getTransactions(TransactionsFilter  .builder()
																		.rental(3200179)
																		.build()))  .isInstanceOf(Transactions.class)
																					.extracting("transactions")
																					.asList()
																					.first()
																					.isInstanceOf(Transaction.class);

	}

}
