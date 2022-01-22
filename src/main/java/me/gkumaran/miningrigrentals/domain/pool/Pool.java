package me.gkumaran.miningrigrentals.domain.pool;

import lombok.Data;

@Data
public class Pool
{
	private Integer priority;
	private String type;
	private String host;
	private Integer port;
	private String user;
	private String pass;
	private String status;
	private String name;
	private Long id;
	private String pool_option1;
}
