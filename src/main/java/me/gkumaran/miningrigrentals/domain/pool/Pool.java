package me.gkumaran.miningrigrentals.domain.pool;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty("status_time")
	private String statusTime;
	private String name;
	private Long id;
	@JsonProperty("pool_option1")
	private String poolOption1;
	private String notes;
}
