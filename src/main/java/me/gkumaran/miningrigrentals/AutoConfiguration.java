package me.gkumaran.miningrigrentals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("file:config.properties")
class AutoConfiguration
{
	@Autowired
	private Environment env;

	@Bean
	@ConditionalOnProperty(
	{ "mrr.key", "mrr.secret" })
	@ConditionalOnMissingBean
	public ApiClient MiningRigRentals()
	{
		final Api miningRigRentalsApi = Api .builder()
											.apiKey(env.getProperty("mrr.key"))
											.apiSecret(env.getProperty("mrr.secret"))
											.build()
											.toBuilder()
											.build();

		return ApiClient.builder()
						.miningRigRentalsApi(miningRigRentalsApi)
						.miningRigRentalsApiService(miningRigRentalsApi.createService(ApiService.class))
						.build();
	}

}
