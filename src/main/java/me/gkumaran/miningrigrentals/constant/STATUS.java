package me.gkumaran.miningrigrentals.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public enum STATUS
{
	DISABLED("disabled"), ENABLED("enabled"), ONLINE("online"), OFFLINE("offline"), AVAILABLE("available"), RENTED("rented"), PENDING("pending"), ERROR("");

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
