package me.gkumaran.miningrigrentals.domain.rig;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import me.gkumaran.miningrigrentals.domain.common.Hashrate;

@Data
public class Rigs
{
	private Integer offset;
	private Integer count;
	private Integer total;
	private Stat stats;
	private ArrayList<Rig> records;

	@Data
	private static class Stat
	{
		private Statistics available;
		private Statistics rented;
		private Price prices;

		@Data
		private static class Statistics
		{
			private Integer rigs;
			private Hashrate hash;
		}

		@Data
		private static class Price
		{
			private BigDecimal lowest;
			@JsonProperty("last_10")
			private BigDecimal last10;
			private BigDecimal last;
		}
	}
}
