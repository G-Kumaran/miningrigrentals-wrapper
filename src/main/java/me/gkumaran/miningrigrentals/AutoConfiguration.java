package me.gkumaran.miningrigrentals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AutoConfiguration
{
	@Autowired
	private Environment env;

	public static ApiClient buildClient(String mrrKey, String mrrSecret)
	{
		final Api miningRigRentalsApi = Api .builder()
											.apiKey(mrrKey)
											.apiSecret(mrrSecret)
											.build()
											.toBuilder()
											.build();
		return ApiClient.builder()
						.miningRigRentalsApi(miningRigRentalsApi)
						.miningRigRentalsApiService(miningRigRentalsApi.createService(ApiService.class))
						.build();
	}

	@Bean
	@ConditionalOnMissingBean @ConditionalOnProperty({ "mrr.key", "mrr.secret" })
	ApiClient MiningRigRentals()
	{
		return buildClient(env.getProperty("mrr.key"), env.getProperty("mrr.secret"));
	}
}
