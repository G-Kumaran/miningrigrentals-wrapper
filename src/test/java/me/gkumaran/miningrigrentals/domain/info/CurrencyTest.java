package me.gkumaran.miningrigrentals.domain.info;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import lombok.var;
import lombok.extern.slf4j.Slf4j;
import me.gkumaran.miningrigrentals.ApiClientTest;

@Slf4j
class CurrencyTest extends ApiClientTest
{

	@Test
	void test()
	{
		final var response = miningRigRentals.getCurrencies();
		assertThat(response).isInstanceOf(Currencies.class);
		assertThat(response .getCurrencies()
							.get(0)).isInstanceOf(me.gkumaran.miningrigrentals.domain.info.Currency.class);

		log.info("Response : {}", response);
	}

}
