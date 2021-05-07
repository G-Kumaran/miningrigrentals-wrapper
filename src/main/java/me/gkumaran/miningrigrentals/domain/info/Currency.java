package me.gkumaran.miningrigrentals.domain.info;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Currency
{
	private String name;
	private Boolean enabled;
	private BigDecimal txfee;
}
