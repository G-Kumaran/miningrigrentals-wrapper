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
@TestPropertySource(locations = "/application.properties")
public class ApiTest
{
	@Autowired
	private Environment env;
	public ApiClient miningRigRentals;
	public Integer rigId;
	public Integer rentalId;
	public Integer rentalIdBuy;

	@BeforeAll
	public void setUp() throws Exception
	{
		final Api miningRigRentalsApi = Api .builder()
											.apiKey(env.getRequiredProperty("mrr.key"))
											.apiSecret(env.getRequiredProperty("mrr.secret"))
											.failOnUnknownProperties(true)
											.build()
											.toBuilder()
											.build();
		miningRigRentals = ApiClient.builder()
									.miningRigRentalsApi(miningRigRentalsApi)
									.miningRigRentalsApiService(miningRigRentalsApi.createService(ApiService.class))
									.build();
		rigId = env.getRequiredProperty("testing.rigid", Integer.class);
		rentalId = env.getRequiredProperty("testing.rentalid.sell", Integer.class);
		rentalIdBuy = env.getRequiredProperty("testing.rentalid.buy", Integer.class);
	}
}
