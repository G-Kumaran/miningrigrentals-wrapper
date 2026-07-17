package me.gkumaran.miningrigrentals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
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
	private final ApiSign apiSign;

	private RestClient buildClient(String mrrUrl, String mrrKey, String mrrSecret)
	{
		return RestClient   .builder()
							.baseUrl(mrrUrl)
							.configureMessageConverters(converters -> converters.registerDefaults()
																				.withJsonConverter(new org.springframework.http.converter.json.JacksonJsonHttpMessageConverter(jsonMapper)))
							.requestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
							.requestInterceptor((request, body, execution) ->
							{
								final Instant timeStamp = Instant.now();
								final String xApiNonce = String.valueOf(timeStamp.getEpochSecond() * 1000000000 + timeStamp.getNano());
								final String signature = apiSign.getSign(mrrKey, mrrSecret, xApiNonce, request  .getURI()
																												.getPath()
																												.replace("/api/v2", ""));
								request .getHeaders()
										.add("x-api-nonce", xApiNonce);
								request .getHeaders()
										.add("x-api-key", mrrKey);
								request .getHeaders()
										.add("x-api-sign", signature);
								ClientHttpResponse response = execution.execute(request, body);
								if (response.getStatusCode()
											.is2xxSuccessful())
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
								return response;
							})
							.defaultStatusHandler(HttpStatusCode::isError, (request, response) ->
							{
								byte[] errorBytes = response.getBody()
															.readAllBytes();
								try
								{
									ApiError apiError = jsonMapper.readValue(errorBytes, ApiError.class);
									throw new ApiException(apiError);
								} catch (Exception e)
								{
									String rawError = new String(errorBytes, StandardCharsets.UTF_8);
									throw new ApiException("Unexpected MRR API Error Format ["
															+ response.getStatusCode()
															+ "]: "
															+ rawError, e);
								}
							})
							.build();
	}

	@Bean
	ApiService apiService(ApiProperties properties)
	{
		return HttpServiceProxyFactory  .builderFor(RestClientAdapter.create(buildClient(properties.getBaseUrl(), properties.getKey(), properties.getSecret())))
										.build()
										.createClient(ApiService.class);
	}

	@Bean
	ApiClient mrrApiClient(ApiService apiService)
	{
		return new ApiClient(apiService);
	}
}
