package me.gkumaran.miningrigrentals;

import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ApiSign
{
	String getSign(String apiKey, String apiSecret, String xApiNonce, String reqeustPath)
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
}
