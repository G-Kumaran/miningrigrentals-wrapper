package me.gkumaran.miningrigrentals.constant;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceConvertor
{
	public static BigDecimal Convert(final BigDecimal hash, final BigDecimal from, final BigDecimal to)
	{
		return hash .multiply(to)
					.divide(from, 8, RoundingMode.HALF_DOWN);
	}

	public static BigDecimal Convert(final BigDecimal hash, final HASHRATE from, final HASHRATE to)
	{
		return Convert(hash, from.getFactor(), to.getFactor());
	}

	public static BigDecimal Convert(final BigDecimal hash, final String from, final BigDecimal to)
	{
		return Convert(hash, HASHRATE   .valueOf(from.toUpperCase())
										.getFactor(), to);
	}

	public static BigDecimal Convert(final String hash, final HASHRATE from, final HASHRATE to)
	{
		return Convert(new BigDecimal(hash), from.getFactor(), to.getFactor());
	}
}
