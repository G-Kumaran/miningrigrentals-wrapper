package me.gkumaran.miningrigrentals.domain.rig.input;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import me.gkumaran.miningrigrentals.ApiClientTest;

@Slf4j
@Disabled
class PoolConfigTest extends ApiClientTest
{
	@Test
	void test()
	{
		lombok.val response = miningRigRentals.putRigPool(98039, PoolConfig .builder()
																			.priority(1)
																			.host("lyra2z.in.nicehash.com")
																			.port(3366)
																			.user("3CKN3zdC7FJD15GcKbGDpWM9fDvAzZb3Bb")
																			.pass("x")
																			.build());
		log.info("Response : {}", response);
		assertThat(response).isInstanceOf(me.gkumaran.miningrigrentals.domain.common.Success.class)
							.extracting("success")
							.isSameAs(Boolean.TRUE);
	}
}
