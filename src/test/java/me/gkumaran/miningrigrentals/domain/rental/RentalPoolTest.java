package me.gkumaran.miningrigrentals.domain.rental;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import me.gkumaran.miningrigrentals.ApiClientTest;

@Slf4j
class RentalPoolTest extends ApiClientTest
{
	@Test
	void getPoolTest()
	{
		lombok.val response = miningRigRentals.getRentalPools(3302319);
		log.info("Response : {}", response);
		assertThat(response).isInstanceOf(me.gkumaran.miningrigrentals.domain.rig.RigPool.class);
	}

	@Test
	void getPoolListTest()
	{
		lombok.val response = miningRigRentals.getRentalPools(Arrays.array(3302319, 3302320));
		log.info("Response : {}", response);
		assertThat(response).isInstanceOf(ArrayList.class)
							.asList()
							.first()
							.isInstanceOf(me.gkumaran.miningrigrentals.domain.rig.RigPool.class);
	}
}
