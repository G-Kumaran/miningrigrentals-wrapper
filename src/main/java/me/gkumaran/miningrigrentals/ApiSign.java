package me.gkumaran.miningrigrentals;

import java.nio.charset.Charset;

import lombok.Builder;
import lombok.NonNull;
import okio.ByteString;

@Builder
class ApiSign
{
	@NonNull
	private String apiSecret;
	@NonNull
	private String apiKey;
	@NonNull
	private String xApiNonce;
	@NonNull
	private String reqeustPath;

	String getSign()
	{
		try
		{
			return ByteString   .encodeString(apiKey.concat(xApiNonce)
													.concat(reqeustPath), Charset.defaultCharset())
								.hmacSha1(ByteString.encodeString(apiSecret, Charset.defaultCharset()))
								.hex();
		} catch (Exception e)
		{
			throw new RuntimeException("Cannot create HMAC Signature", e);
		}
	}
}
