package me.gkumaran.miningrigrentals.domain.rental;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Rentals
{
	private Integer total;
	private Integer returned;
	private Integer start;
	private Integer limit;
	private ArrayList<Rental> rentals;
}
