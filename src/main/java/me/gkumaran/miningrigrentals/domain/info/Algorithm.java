package me.gkumaran.miningrigrentals.domain.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import me.gkumaran.miningrigrentals.domain.common.Hashrate;
import me.gkumaran.miningrigrentals.domain.common.Price;

@Data
public class Algorithm
{
	private String name;
	private String display;
	@JsonProperty("suggested_price")
	private Price suggestedPrice;
	private Stats stats;
	@JsonProperty("new")
	private Boolean newAlgorithm;
	private Boolean hot;
	private me.gkumaran.miningrigrentals.constant.HASHRATE hashtype;
	@JsonProperty("pool_option1")
	private String poolOption1;

	@Data
	public static class Stats
	{
		private rigs available;
		private rigs rented;
		private prices prices;

		@Data
		public static class rigs
		{
			private Integer rigs;
			private Hashrate hash;
		}

		@Data
		public static class prices
		{
			private Price lowest;
			@JsonProperty("last_10")
			private Price last10;
			@JsonProperty("last_20")
			private Price last20;
			@JsonProperty("last_30")
			private Price last30;
			private Price last;
		}
	}
}
