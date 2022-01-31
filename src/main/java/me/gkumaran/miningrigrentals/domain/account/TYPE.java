package me.gkumaran.miningrigrentals.domain.account;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TYPE
{
	CREDIT("Credit"),
	PAYOUT("Payout"),
	REFERRAL("Referral"),
	DEPOSIT("Deposit"),
	PAYMENT("Payment"),
	REFUND_CREDIT("Credit/Refund"),
	REFUND_DEBIT("Debit/Refund"),
	RENTAL_FEE("Rental Fee");

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
		return this.tag;
	}

	public static TYPE parse(String tagValue)
	{
		return Arrays   .asList(TYPE.values())
						.stream()
						.filter(tag -> tag  .getTag()
											.equalsIgnoreCase(tagValue))
						.findFirst()
						.get();
	}
}
