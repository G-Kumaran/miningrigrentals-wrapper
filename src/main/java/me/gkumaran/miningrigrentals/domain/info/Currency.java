package me.gkumaran.miningrigrentals.domain.info;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Currency
{
	private String name;
	private Boolean enabled;
	private BigDecimal txfee;
	@JsonProperty("min_withdraw")
	private BigDecimal minWithdraw;
}
