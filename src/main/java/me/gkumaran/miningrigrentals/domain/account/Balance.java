package me.gkumaran.miningrigrentals.domain.account;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Balance
{
	private BigDecimal confirmed;
	private BigDecimal unconfirmed;
}
