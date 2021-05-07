package me.gkumaran.miningrigrentals.domain.rig.input;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RigFilter
{
	@NonNull
	private String type;
	private range minhours;
	private range maxhours;
	private range rpi;
	private hrrange hash;
	private range price;
	@Builder.Default
	private Boolean offline = false;
	@Builder.Default
	private Boolean rented = false;
	private region region;
	@Builder.Default
	private Integer count = 100;
	@Builder.Default
	private Integer offset = 0;
	@Builder.Default
	private ORDERBY orderby = ORDERBY.score;
	@Builder.Default
	private ORDERDIR orderdir = ORDERDIR.asc;

	@Data
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class range
	{
		private BigDecimal min;
		private BigDecimal max;
	}

	@Data
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class hrrange
	{
		private BigDecimal min;
		private BigDecimal max;
		private me.gkumaran.miningrigrentals.constant.HASHRATE type;
	}

	@Data
	public static class region
	{
		private REGIONTYPE type;

		private enum REGIONTYPE
		{
			include, exclude;
		}
	}

	public enum ORDERBY
	{
		rpi, hash, price, minhrs, maxhrs, score;
	}

	public enum ORDERDIR
	{
		asc, dsc;
	}
}
