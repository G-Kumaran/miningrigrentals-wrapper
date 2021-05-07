package me.gkumaran.miningrigrentals.domain.rig.input;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PoolConfig
{

	private int priority;
	@NonNull
	private String host;
	@NonNull
	private Integer port;
	@NonNull
	private String user;
	@NonNull
	private String pass;
}