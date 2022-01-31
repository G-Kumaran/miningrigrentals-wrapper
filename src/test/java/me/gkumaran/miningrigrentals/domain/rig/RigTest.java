package me.gkumaran.miningrigrentals.domain.rig;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import me.gkumaran.miningrigrentals.ApiClientTest;

@Slf4j
class RigTest extends ApiClientTest
{
	@Test
	void getRigsTest()
	{
		final me.gkumaran.miningrigrentals.domain.rig.input.RigFilter rigFilter = me.gkumaran.miningrigrentals.domain.rig.input.RigFilter   .builder()
																																			.type("kawpow")
																																			.build();
		final lombok.val response = miningRigRentals.getRigs(rigFilter);
		assertThat(response).isInstanceOf(me.gkumaran.miningrigrentals.domain.rig.Rigs.class);
		assertThat(response .getRecords()
							.get(0)).isInstanceOf(me.gkumaran.miningrigrentals.domain.rig.Rig.class);
		log.info("Response : {}", response);
	}

	@Test
	void getRigsMineTest()
	{
		final lombok.val response = miningRigRentals.getRigsMine();
		assertThat(response).isInstanceOf(ArrayList.class);
		assertThat(response.get(0)).isInstanceOf(me.gkumaran.miningrigrentals.domain.rig.Rig.class);
		log.info("Response : {}", response);
	}

	@Test
	void getRigsMineParamTest()
	{
		final lombok.val response = miningRigRentals.getRigsMine(null, true);
		assertThat(response).isInstanceOf(ArrayList.class);
		assertThat(response.get(0)).isInstanceOf(me.gkumaran.miningrigrentals.domain.rig.Rig.class);
		log.info("Response : {}", response);
	}
}
