package me.gkumaran.miningrigrentals.domain.info;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lombok.var;
import lombok.extern.slf4j.Slf4j;
import me.gkumaran.miningrigrentals.ApiClientTest;

@Slf4j
class ServersTest extends ApiClientTest
{
	@Test
	void getServersTest()
	{
		final var response = miningRigRentals.getServers();
		assertThat(response).isInstanceOf(ArrayList.class);
		assertThat(response.get(0)).isInstanceOf(me.gkumaran.miningrigrentals.domain.info.Server.class);

		log.info("Response : {}", response);
	}

}
