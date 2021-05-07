package me.gkumaran.miningrigrentals.domain.rig;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import me.gkumaran.miningrigrentals.ApiClientTest;

class RigTest extends ApiClientTest
{
	@Test
	void getRigsTest()
	{
		final me.gkumaran.miningrigrentals.domain.rig.input.RigFilter rigFilter = me.gkumaran.miningrigrentals.domain.rig.input.RigFilter   .builder()
																																			.type("kawpow")
																																			.build();
		assertThat(miningRigRentals.getRigs(rigFilter)).isInstanceOf(me.gkumaran.miningrigrentals.domain.rig.Rigs.class);
		assertThat(miningRigRentals .getRigs(rigFilter)
									.getRecords()
									.get(0)).isInstanceOf(me.gkumaran.miningrigrentals.domain.rig.Rig.class);
	}

	@Test
	void getRigsMineTest()
	{
		assertThat(miningRigRentals.getRigsMine()).isInstanceOf(ArrayList.class);
		assertThat(miningRigRentals .getRigsMine()
									.get(0)).isInstanceOf(me.gkumaran.miningrigrentals.domain.rig.Rig.class);
	}

	@Test
	void getRigsMineParamTest()
	{
		assertThat(miningRigRentals.getRigsMine(null, true)).isInstanceOf(ArrayList.class);
		assertThat(miningRigRentals .getRigsMine(null, true)
									.get(0)).isInstanceOf(me.gkumaran.miningrigrentals.domain.rig.Rig.class);
	}

}
