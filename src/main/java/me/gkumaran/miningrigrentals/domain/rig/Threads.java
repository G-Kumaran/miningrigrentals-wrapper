package me.gkumaran.miningrigrentals.domain.rig;

import java.math.BigDecimal;
import java.util.ArrayList;

import lombok.Data;

@Data
public class Threads
{
	private Integer rigid;
	private String access;
	private ArrayList<thread> threads;

	@Data
	public static class thread
	{
		private shares shares;
		private pool pool;
		private errors errors;
		private String guid;
		private difficulty difficulty;
		private String region;
		private miner miner;
		private hashrate hashrate;

		@Data
		public static class shares
		{
			private Long accepted;
			private Long rejected;
			private Long total;
		}

		@Data
		public static class pool
		{
			private String rented;
			private String priority;
		}

		@Data
		public static class errors
		{
			private Long clientreconn;
			private Long sickpool;
			private Long maxbadshare;
			private Long hostnotfound;
			private Long misc;
			private Long nopools;
		}

		@Data
		public static class difficulty
		{
			private BigDecimal share;
			private BigDecimal network;
		}

		@Data
		public static class miner
		{
			private String version;
			private String xnonce;
			private String pass;
			private String ip;
		}

		@Data
		public static class hashrate
		{
			private BigDecimal valid;
			private String valid_nice;
		}
	}
}
