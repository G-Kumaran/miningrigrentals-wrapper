package me.gkumaran.miningrigrentals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HexFormat;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import lombok.RequiredArgsConstructor;
import me.gkumaran.miningrigrentals.exception.ApiError;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ApiProperties.class)
public class ApiConfiguration
{
	private final JsonMapper jsonMapper;

	private RestClient buildClient(String mrrUrl, String mrrKey, String mrrSecret)
	{
		return RestClient   .builder()
							.baseUrl(mrrUrl)
							.configureMessageConverters(converters -> converters.registerDefaults()
																				.withJsonConverter(new org.springframework.http.converter.json.JacksonJsonHttpMessageConverter(jsonMapper)))
							.requestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
							.requestInterceptor(mrrRequestInterceptor(mrrKey, mrrSecret))
							.defaultStatusHandler(HttpStatusCode::isError, (request, response) ->
							{
								byte[] errorBytes = response.getBody()
															.readAllBytes();
								ApiError apiError;
								try
								{
									apiError = jsonMapper.readValue(errorBytes, ApiError.class);
								} catch (Exception e)
								{
									String rawError = new String(errorBytes, StandardCharsets.UTF_8);
									throw new ApiException("Unexpected MRR API Error Format ["
															+ response.getStatusCode()
															+ "]: "
															+ rawError, e);
								}
								throw new ApiException(apiError);
							})
							.build();
	}

	private ClientHttpRequestInterceptor mrrRequestInterceptor(String mrrKey, String mrrSecret)
	{
		return (request, body, execution) ->
		{
			final Instant timeStamp = Instant.now();
			final String xApiNonce = String.valueOf(timeStamp.getEpochSecond() * 1000000000 + timeStamp.getNano());
			final String signature = this.mrrApiSign(mrrKey, mrrSecret, xApiNonce, request  .getURI()
																							.getPath()
																							.replace("/api/v2", ""));
			request .getHeaders()
					.add("x-api-nonce", xApiNonce);
			request .getHeaders()
					.add("x-api-key", mrrKey);
			request .getHeaders()
					.add("x-api-sign", signature);
			ClientHttpResponse response = execution.execute(request, body);
			return response .getStatusCode()
							.is2xxSuccessful() ? mrrWrapperResponse(response) : response;
		};
	}

	private String mrrApiSign(String apiKey, String apiSecret, String xApiNonce, String reqeustPath) throws ApiException
	{
		try
		{
			final var mac = Mac.getInstance("HmacSHA1");
			final var secretKey = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA1");
			mac.init(secretKey);
			final var data = apiKey + xApiNonce + reqeustPath;
			final byte[] signatureBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
			return HexFormat.of()
							.formatHex(signatureBytes);
		} catch (Exception e)
		{
			throw new ApiException("Failed to generate MRR HMAC signature: ", e);
		}
	}

	private ClientHttpResponse mrrWrapperResponse(ClientHttpResponse response) throws IOException
	{
		return new ClientHttpResponse()
		{
			JsonNode rootNode = jsonMapper.readTree(response.getBody());

			@Override
			public HttpStatusCode getStatusCode() throws IOException
			{
				return rootNode .path("success")
								.asBoolean(true) ? response.getStatusCode() : HttpStatusCode.valueOf(400);
			}

			@Override
			public String getStatusText() throws IOException
			{
				return response.getStatusText();
			}

			@Override
			public void close()
			{
				response.close();
			}

			@Override
			public org.springframework.http.HttpHeaders getHeaders()
			{
				return response.getHeaders();
			}

			@Override
			public java.io.InputStream getBody()
			{
				return new java.io.ByteArrayInputStream(jsonMapper.writeValueAsBytes(rootNode.path("data")));
			}
		};
	}

	@Bean
	ApiService mrrApiService(ApiProperties properties)
	{
		return HttpServiceProxyFactory  .builderFor(RestClientAdapter.create(buildClient(properties.getBaseUrl(), properties.getKey(), properties.getSecret())))
										.build()
										.createClient(ApiService.class);
	}

	@Bean
	ApiClient mrrApiClient(ApiService mrrApiService)
	{
		return new ApiClient(mrrApiService);
	}
}
