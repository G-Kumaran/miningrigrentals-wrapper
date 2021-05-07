package me.gkumaran.miningrigrentals.domain.rental;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Message
{
	public Long rentalid;
	public ArrayList<message> messages;

	@Data
	public static class message
	{
		public String username;
		public String is_admin;
		public String is_support;
		public String when;
		public String message;
	}
}
