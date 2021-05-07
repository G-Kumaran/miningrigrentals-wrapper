package me.gkumaran.miningrigrentals.domain.account;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Transactions
{
	private Integer total;
	private Integer returned;
	private Integer start;
	private Integer limit;
	private ArrayList<Transaction> transactions;
}
