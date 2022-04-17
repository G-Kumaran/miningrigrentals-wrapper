package me.gkumaran.miningrigrentals.domain.common;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Hashrate
{
	private BigDecimal hash;
	private String unit;
	private String nice;
	private String type;
	private BigDecimal percent;
}
