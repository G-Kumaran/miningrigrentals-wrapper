package me.gkumaran.miningrigrentals.domain.rig;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Pool
{
	private Integer rigid;
	private ArrayList<pool> pools;

	@Data
	public static class pool
	{
		private Integer priority;
		private String type;
		private String host;
		private Integer port;
		private String user;
		private String pass;
		private String status;
	}
}
