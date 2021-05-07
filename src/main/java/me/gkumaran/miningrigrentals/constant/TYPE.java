package me.gkumaran.miningrigrentals.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TYPE
{
	OWNER("owner"), RENTER("renter");

	private final String tag;

	private TYPE(String tag)
	{
		this.tag = tag;
	}

	@JsonValue
	public String getTag()
	{
		return this.tag;
	}

	@Override
	public String toString()
	{
		return tag;
	}
}
