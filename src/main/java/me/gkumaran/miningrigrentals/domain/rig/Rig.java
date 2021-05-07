package me.gkumaran.miningrigrentals.domain.rig;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import me.gkumaran.miningrigrentals.domain.common.Hashrate;

@Data
public class Rig
{
	private Integer id;
	private String name;
	private String owner;
	private String type;
	private Status status;
	private Boolean online;
	private String xnonce;
	private String poolstatus;
	private String region;
	private String rpi;
	@JsonProperty("suggested_diff")
	private BigDecimal suggestedDiff;
	@JsonProperty("optimal_diff")
	private OptimalDiff optimalDiff;
	private Long ndevices;
	@JsonProperty("device_memory")
	private Long deviceMemory;
	private Boolean extensions;
	private Price price;
	private BigDecimal minhours;
	private BigDecimal maxhours;
	private Hash hashrate;
	private String description;
	@JsonProperty("available_status")
	private me.gkumaran.miningrigrentals.constant.STATUS availableStatus;
	@JsonProperty("rental_id")
	private String rentalId;
	@JsonProperty("renter_id")
	private String renterId;
	private Long riggroup;
	private String shorturl;
	@JsonProperty("device_ram")
	private BigDecimal deviceRam;

	@Data
	public static class Status
	{
		private me.gkumaran.miningrigrentals.constant.STATUS status;
		private BigDecimal hours;
		private Boolean online;
		private Boolean rented;
		@JsonProperty("rental_id")
		private String rentalId;
	}

	@Data
	public static class OptimalDiff
	{
		private BigDecimal min;
		private BigDecimal max;
	}

	@Data
	public static class Price
	{
		private me.gkumaran.miningrigrentals.constant.HASHRATE type;
		@JsonProperty("BTC")
		private Pricing btc;
		@JsonProperty("LTC")
		private Pricing ltc;
		@JsonProperty("ETH")
		private Pricing eth;
		@JsonProperty("DASH")
		private Pricing dash;
		@JsonProperty("BCH")
		private Pricing bch;

		@Data
		public static class Pricing
		{
			private String currency;
			private BigDecimal price;
			private BigDecimal hour;
			private BigDecimal minhrs;
			private BigDecimal maxhrs;
			private Boolean enabled;
			private Boolean autoprice;
			private BigDecimal minimum;
			private BigDecimal modifier;
		}
	}

	@Data
	public static class Hash
	{
		private Hashrate advertised;
		@JsonProperty("last_5min")
		private Hashrate last5min;
		@JsonProperty("last_15min")
		private Hashrate last15min;
		@JsonProperty("last_30min")
		private Hashrate last30min;
	}
}
