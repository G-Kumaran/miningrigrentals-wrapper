package me.gkumaran.miningrigrentals.domain.riggroup;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import me.gkumaran.miningrigrentals.ApiClientTest;

class RigGroupTest extends ApiClientTest
{
	@Test
	void getRigGroupTest()
	{
		assertThat(miningRigRentals.getRigGroup())  .isInstanceOf(HashMap.class)
													.extractingByKey(4L)
													.isInstanceOf(me.gkumaran.miningrigrentals.domain.riggroup.RigGroup.class);
	}
}
