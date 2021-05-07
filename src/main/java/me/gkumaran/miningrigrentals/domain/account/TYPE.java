package me.gkumaran.miningrigrentals.domain.account;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TYPE
{
	CREDIT("Credit"), PAYOUT("Payout"), REFERRAL("Referral"), DEPOSIT("Deposit"), PAYMENT("Payment"), REFUND_CREDIT("Credit/Refund"), REFUND_DEBIT("Debit/Refund"), RENTAL_FEE("Rental Fee");

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
}
