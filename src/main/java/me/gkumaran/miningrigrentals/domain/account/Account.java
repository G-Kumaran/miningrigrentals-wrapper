package me.gkumaran.miningrigrentals.domain.account;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Account
{
	private String username;
	private String email;
	private Withdraw_Currency withdraw;
	private Deposit_Currency deposit;
	private Notifications notifications;
	private Settings settings;

	@Data
	public static class Withdraw_Currency
	{
		@JsonProperty("BTC")
		private Withdraw BTC;
		@JsonProperty("LTC")
		private Withdraw LTC;
		@JsonProperty("DASH")
		private Withdraw DASH;
		@JsonProperty("ETH")
		private Withdraw ETH;
		@JsonProperty("BCH")
		private Withdraw BCH;
		@JsonProperty("DOGE")
		private Withdraw DOGE;
	}

	@Data
	public static class Withdraw
	{
		private String address;
		private String label;
		@JsonProperty("auto_pay_threshold")
		private BigDecimal autoPayThreshold;
		private BigDecimal txfee;
	}

	@Data
	public static class Deposit_Currency
	{
		@JsonProperty("BTC")
		private Deposit BTC;
		@JsonProperty("LTC")
		private Deposit LTC;
		@JsonProperty("DASH")
		private Deposit DASH;
		@JsonProperty("ETH")
		private Deposit ETH;
		@JsonProperty("BCH")
		private Deposit BCH;
		@JsonProperty("DOGE")
		private Deposit DOGE;
	}

	@Data
	public static class Deposit
	{
		private String address;
	}

	@Data
	public class Notifications
	{
		@JsonProperty("rental_comm")
		private String rentalComm;
		@JsonProperty("new_rental")
		private String newRental;
		private String offline;
		private String news;
		private String deposit;
	}

	@Data
	public class Settings
	{
		@JsonProperty("live_data")
		private String liveData;
		@JsonProperty("public_profile")
		private String publicProfile;
		@JsonProperty("2factor_auth")
		private String twoFactorAuth;
	}
}
