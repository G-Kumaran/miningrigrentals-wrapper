package me.gkumaran.miningrigrentals.domain.info;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import me.gkumaran.miningrigrentals.ApiClientTest;

@Slf4j
class AlgorithmTest extends ApiClientTest
{
	@Test
	void test()
	{
		final lombok.val response = miningRigRentals.getAlgorithm("scrypt");
		assertThat(response).isInstanceOf(Algorithm.class);
		log.info("Response : {}", response);
	}

	@Test
	void testCurr()
	{
		final lombok.val response = miningRigRentals.getAlgorithm("scrypt", "ETH");
		assertThat(response).isInstanceOf(Algorithm.class);
		log.info("Response : {}", response);
	}

	@Test
	void testMultiple()
	{
		final lombok.val response = miningRigRentals.getAlgorithms();
		assertThat(response).isInstanceOf(ArrayList.class);
		assertThat(response.get(0)).isInstanceOf(me.gkumaran.miningrigrentals.domain.info.Algorithm.class);
		log.info("Response : {}", response);
	}
}
