package me.gkumaran.miningrigrentals.domain.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import me.gkumaran.miningrigrentals.ApiClientTest;

@Slf4j
class BalanceTest extends ApiClientTest
{
	@Test
	void test()
	{
		final lombok.val response = miningRigRentals.getBalances();
		assertThat(response).isInstanceOf(HashMap.class)
							.extractingByKey("BTC")
							.isInstanceOf(me.gkumaran.miningrigrentals.domain.account.Balance.class);
		log.info("Response : {}", response);
	}
}
