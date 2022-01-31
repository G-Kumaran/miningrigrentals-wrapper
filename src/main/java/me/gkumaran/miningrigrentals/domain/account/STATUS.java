package me.gkumaran.miningrigrentals.domain.account;

import com.fasterxml.jackson.annotation.JsonValue;

public enum STATUS
{
	PENDING("Pending"),
	CLEARED("Cleared");

	private final String tag;

	private STATUS(String tag)
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
		return this.tag;
	}
}
