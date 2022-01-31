package me.gkumaran.miningrigrentals.domain.rig;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Graph
{
	private Integer rigid;
	private chartdata chartdata;
	private me.gkumaran.miningrigrentals.constant.HASHRATE hashtype;
	private advertised advertised;

	@Data
	public static class chartdata
	{
		private String bars;
		private String average;
		private String rejected;
		private String rentals;
		private String offline;
		private String pooloffline;
	}

	@Data
	public static class advertised
	{
		private BigDecimal raw;
		private BigDecimal hashtype;
	}
}
