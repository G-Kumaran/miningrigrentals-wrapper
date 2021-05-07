package me.gkumaran.miningrigrentals.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PRICE
{
	HASH("hash*day",
			Double  .valueOf(Math.pow(10, 0))
					.longValue()),
	H("h*day",
			Double  .valueOf(Math.pow(10, 0))
					.longValue()),
	KH("kh*day",
			Double  .valueOf(Math.pow(10, 3))
					.longValue()),
	MH("mh*day",
			Double  .valueOf(Math.pow(10, 6))
					.longValue()),
	GH("gh*day",
			Double  .valueOf(Math.pow(10, 9))
					.longValue()),
	TH("th*day",
			Double  .valueOf(Math.pow(10, 12))
					.longValue()),
	PH("ph*day",
			Double  .valueOf(Math.pow(10, 15))
					.longValue());

	private final String tag;
	private final Long factor;

	private PRICE(String tag, Long factor)
	{
		this.tag = tag;
		this.factor = factor;
	}

	@JsonValue
	public String getTag()
	{
		return this.tag;
	}

	public Long getFactor()
	{
		return this.factor;
	}

	@Override
	public String toString()
	{
		return this.tag;
	}
}