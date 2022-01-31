package me.gkumaran.miningrigrentals.domain.rental;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import me.gkumaran.miningrigrentals.constant.HASHRATE;

@Data
public class ExtendResponse
{
	private Integer rentalid;
	private Integer rigid;
	private BigDecimal maxhrs;
	@JsonProperty("current_hrs")
	private BigDecimal currenthrs;
	private Rate rate;
	private me.gkumaran.miningrigrentals.domain.common.Hashrate hashrate;
	private String currency;
	private BigDecimal cost;
	private Boolean success;
	@JsonProperty("new_hrs")
	private BigDecimal newhrs;
	private String message;

	@Data
	public static class Rate
	{
		private BigDecimal price;
		private BigDecimal hashrate;
		private HASHRATE type;
	}
}
