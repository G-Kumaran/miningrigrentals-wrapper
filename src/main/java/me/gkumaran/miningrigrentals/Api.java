package me.gkumaran.miningrigrentals;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.time.Instant;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.okhttp3.OkHttpMetricsEventListener;
import lombok.Builder;
import me.gkumaran.miningrigrentals.exception.ApiError;
import me.gkumaran.miningrigrentals.wrappedresponse.UnwrapConverterFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Builder(toBuilder = true)
class Api
{
	@Builder.Default
	private final String API_BASE_URL = "https://www.miningrigrentals.com/api/v2/";
	@Builder.Default
	private Boolean failOnUnknownProperties = false;

	@Builder.ObtainVia(method = "buildRetrofit")
	private Retrofit retrofit;

	private String apiKey;
	private String apiSecret;

	private Retrofit buildRetrofit()
	{
		return new Retrofit.Builder()   .baseUrl(API_BASE_URL)
										.client(new OkHttpClient.Builder()  .eventListener(OkHttpMetricsEventListener   .builder(Metrics.globalRegistry, "okhttp.requests")
																														.uriMapper(request -> request   .url()
																																						.encodedPath())
																														.tags(Tags.of("api", "miningrigrentals-v2", "key", apiKey))
																														.build())
																			.build()
																			.newBuilder()
																			.addInterceptor(chain ->
																			{
																				final String xApiNonce = String.valueOf(Instant .now()
																																.toEpochMilli());
																				final Request orginalRequest = chain.request()
																													.newBuilder()
																													.build();
																				final Buffer requestBody = new Buffer();
																				if (orginalRequest.body() != null)
																					orginalRequest  .body()
																									.writeTo(requestBody);

																				final Request modifiedRequest = chain   .request()
																														.newBuilder()
																														.addHeader("x-api-nonce", xApiNonce)
																														.addHeader("x-api-key", apiKey)
																														.addHeader("x-api-sign", ApiSign.builder()
																																						.apiSecret(apiSecret)
																																						.apiKey(apiKey)
																																						.xApiNonce(xApiNonce)
																																						.reqeustPath(orginalRequest .url()
																																													.encodedPath()
																																													.replace("/api/v2", ""))
																																						.build()
																																						.getSign())
																														.build();

																				okhttp3.Response response = chain.proceed(modifiedRequest);

																				if (response.peekBody(16)
																							.string()
																							.endsWith("false"))
																					return response .newBuilder()
																									.code(400)
																									.build();
																				return response;
																			})
																			.build())
										.addConverterFactory(new UnwrapConverterFactory(JacksonConverterFactory.create(new ObjectMapper()   .registerModule(new JavaTimeModule())
																																			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties))))
										.addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()  .registerModule(new JavaTimeModule())
																												.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties)))
										.build();
	}

	public <S> S createService(Class<S> serviceClass)
	{
		return retrofit.create(serviceClass);
	}

	public <T> T executeSync(Call<T> call)
	{
		try
		{
			Response<T> response = call.execute();
			if (!response.isSuccessful())
			{
				try
				{
					ApiError apiError = getApiError(response);
					throw new ApiException(apiError);
				} catch (IOException e)
				{
					throw new ApiException(response.toString(), e);
				}
			}
			return response.body();
		} catch (IOException e)
		{
			throw new ApiException(e);
		}
	}

	private ApiError getApiError(Response<?> response) throws IOException
	{
		return (ApiError) retrofit  .responseBodyConverter(ApiError.class, new Annotation[0])
									.convert(response.errorBody());
	}
}
