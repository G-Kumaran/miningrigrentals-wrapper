package me.gkumaran.miningrigrentals.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import me.gkumaran.miningrigrentals.ApiClientTest;

@Slf4j
class WhoAmITest extends ApiClientTest
{
	@Test
	void test()
	{
		final lombok.val response = miningRigRentals.WhoAmI();
		assertThat(response).isInstanceOf(WhoAmI.class);
		log.info("Response : {}", response);
	}
}
