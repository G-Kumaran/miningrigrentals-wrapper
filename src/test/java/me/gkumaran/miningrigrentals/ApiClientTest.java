package me.gkumaran.miningrigrentals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestPropertySource(locations = "/config.properties")
public class ApiClientTest
{
	@Autowired
	private Environment env;
	public ApiClient miningRigRentals;

	@BeforeAll
	public void setUp() throws Exception
	{
		final Api miningRigRentalsApi = Api .builder()
											.apiKey(env.getProperty("mrr.key"))
											.apiSecret(env.getProperty("mrr.secret"))
											.failOnUnknownProperties(true)
											.build()
											.toBuilder()
											.build();
		miningRigRentals = ApiClient.builder()
									.miningRigRentalsApi(miningRigRentalsApi)
									.miningRigRentalsApiService(miningRigRentalsApi.createService(ApiService.class))
									.build();
	}
}
