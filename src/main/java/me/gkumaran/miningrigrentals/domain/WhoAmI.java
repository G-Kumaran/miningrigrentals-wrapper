package me.gkumaran.miningrigrentals.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WhoAmI
{
	private Boolean authed;
	private Integer userid;
	private String username;
	@JsonProperty("api_key")
	private String apiKey;
	@JsonProperty("api_sign")
	private String apisign;
	@JsonProperty("api_nonce")
	private String apiNonce;
	@JsonProperty("auth_mesage")
	private String authMesage;
	private Permissions permissions;

	@Data
	public static class Permissions
	{
		private String withdraw;
		private String rent;
		private String rigs;
	}
}
