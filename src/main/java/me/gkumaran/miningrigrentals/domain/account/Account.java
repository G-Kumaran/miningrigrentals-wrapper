package me.gkumaran.miningrigrentals.domain.account;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Account
{
	public String username;
	public String email;
	public Withdraw_Currency withdraw;
	public Deposit_Currency deposit;
	public Notifications notifications;
	public Settings settings;

	@Data
	public static class Withdraw_Currency
	{
		public Withdraw BTC;
		public Withdraw LTC;
		public Withdraw DASH;
		public Withdraw ETH;
		public Withdraw BCH;
	}

	@Data
	public static class Withdraw
	{
		public String address;
		public String label;
		public BigDecimal auto_pay_threshold;
		public BigDecimal txfee;
	}

	@Data
	public static class Deposit_Currency
	{
		public Deposit BTC;
		public Deposit LTC;
		public Deposit DASH;
		public Deposit ETH;
		public Deposit BCH;
	}

	@Data
	public static class Deposit
	{
		public String address;
	}

	@Data
	public class Notifications
	{
		public String rental_comm;
		public String new_rental;
		public String offline;
		public String news;
		public String deposit;

	}

	@Data
	public class Settings
	{
		public String live_data;
		public String public_profile;
		@JsonProperty("2factor_auth")
		public String two_factor_auth;
	}
}
