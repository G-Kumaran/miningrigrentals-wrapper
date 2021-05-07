package me.gkumaran.miningrigrentals.domain.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Server
{
	private Integer id;
	private String name;
	private String region;
	private Integer port;
	@JsonProperty("ethereum_port")
	private Integer ethereumPort;
	private String info;
}
