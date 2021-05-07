package me.gkumaran.miningrigrentals.domain.common;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Price
{
	private BigDecimal amount;
	private String currency;
	private me.gkumaran.miningrigrentals.constant.PRICE unit;
}
