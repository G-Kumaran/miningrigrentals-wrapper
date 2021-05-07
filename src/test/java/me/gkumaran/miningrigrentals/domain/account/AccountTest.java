package me.gkumaran.miningrigrentals.domain.account;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import lombok.var;
import lombok.extern.slf4j.Slf4j;
import me.gkumaran.miningrigrentals.ApiClientTest;

@Slf4j
class AccountTest extends ApiClientTest
{

	@Test
	void test()
	{
		final var response = miningRigRentals.getAccount();
		assertThat(response).isInstanceOf(Account.class);

		log.info("Response : {}", response);
	}

}
