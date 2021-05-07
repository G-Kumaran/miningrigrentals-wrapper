package me.gkumaran.miningrigrentals.domain.rig;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import me.gkumaran.miningrigrentals.ApiClientTest;

class PoolTest extends ApiClientTest
{
	@Test
	void getPortTest()
	{
		assertThat(miningRigRentals.getPort(162282)).isInstanceOf(me.gkumaran.miningrigrentals.domain.rig.Port.class);
	}

	@Test
	void getPortListTest()
	{
		assertThat(miningRigRentals.getPort(Arrays.array(162282, 161875))).isInstanceOf(ArrayList.class);
		assertThat(miningRigRentals .getPort(Arrays.array(162282, 161875))
									.get(0)).isInstanceOf(me.gkumaran.miningrigrentals.domain.rig.Port.class);
	}

}
