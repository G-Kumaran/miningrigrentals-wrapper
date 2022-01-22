package me.gkumaran.miningrigrentals.domain.rig;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import me.gkumaran.miningrigrentals.ApiClientTest;

@Slf4j
class PortTest extends ApiClientTest
{
	@Test
	void getPortTest()
	{
		lombok.val response = miningRigRentals.getPort(162282);
		log.info("Response : {}", response);
		assertThat(miningRigRentals.getPort(162282)).isInstanceOf(me.gkumaran.miningrigrentals.domain.rig.Port.class);
	}

	@Test
	void getPortListTest()
	{
		lombok.val response = miningRigRentals.getPort(Arrays.array(162282, 161875));
		log.info("Response : {}", response);
		assertThat(response).isInstanceOf(ArrayList.class)
							.asList()
							.first()
							.isInstanceOf(me.gkumaran.miningrigrentals.domain.rig.Port.class);
	}
}
