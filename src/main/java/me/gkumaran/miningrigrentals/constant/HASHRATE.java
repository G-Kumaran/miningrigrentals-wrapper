package me.gkumaran.miningrigrentals.constant;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

public enum HASHRATE
{
	HASH("hash",
			BigDecimal  .valueOf(Math.pow(10, 0))
						.setScale(0)),
	KH("kh",
			BigDecimal  .valueOf(Math.pow(10, 3))
						.setScale(0)),
	MH("mh",
			BigDecimal  .valueOf(Math.pow(10, 6))
						.setScale(0)),
	GH("gh",
			BigDecimal  .valueOf(Math.pow(10, 9))
						.setScale(0)),
	TH("th",
			BigDecimal  .valueOf(Math.pow(10, 12))
						.setScale(0)),
	PH("ph",
			BigDecimal  .valueOf(Math.pow(10, 15))
						.setScale(0)),
	H("h",
			BigDecimal  .valueOf(Math.pow(10, 0))
						.setScale(2));

	private static final Map<String, HASHRATE> BY_TAG = new HashMap<>();
	private static final Map<String, HASHRATE> BY_FACTOR = new HashMap<>();
	static
	{
		for (HASHRATE hr : values())
		{
			BY_TAG.put(hr.getTag(), hr);
			BY_FACTOR.put(hr.getFactor()
							.toPlainString(), hr);
		}
	}
	private final String tag;
	private final BigDecimal factor;

	private HASHRATE(String tag, BigDecimal factor)
	{
		this.tag = tag;
		this.factor = factor;
	}

	@JsonValue
	public String getTag()
	{
		return this.tag;
	}

	public BigDecimal getFactor()
	{
		return this.factor;
	}

	public static HASHRATE valueOfFactor(BigDecimal factor)
	{
		return BY_FACTOR.get(factor .stripTrailingZeros()
									.toPlainString());
	}

	@Override
	public String toString()
	{
		return this.tag;
	}
}