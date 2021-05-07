package me.gkumaran.miningrigrentals.domain.rig.input;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RigConfig
{
	@NonNull
	private Integer id;
	private String name;
	private String description;
	private me.gkumaran.miningrigrentals.constant.STATUS status;
	private String server;
	private price price;
	private BigDecimal minhours;
	private BigDecimal maxhours;
	private hashrate hash;
	private BigDecimal suggested_diff;
	private Integer ndevices;

	@Data
	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class price
	{
		private btc_pricing btc;
		private alt_pricing ltc;
		private alt_pricing eth;
		private alt_pricing dash;
		private alt_pricing bch;
		@NonNull
		private me.gkumaran.miningrigrentals.constant.HASHRATE type;

		@Data
		@Builder
		@JsonInclude(JsonInclude.Include.NON_NULL)
		public static class btc_pricing
		{
			private BigDecimal price;
			private Boolean autoprice;
			private BigDecimal minimum;
			private BigDecimal modifier;
		}

		@Data
		@Builder
		@JsonInclude(JsonInclude.Include.NON_NULL)
		public static class alt_pricing
		{
			private Boolean enabled;
			private BigDecimal price;
			private Boolean autoprice;
		}
	}

	@Data
	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class hashrate
	{
		private BigDecimal hash;
		private me.gkumaran.miningrigrentals.constant.HASHRATE type;
	}
}
