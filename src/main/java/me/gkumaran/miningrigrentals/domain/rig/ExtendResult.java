package me.gkumaran.miningrigrentals.domain.rig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExtendResult
{
	private Integer id;
	private Boolean success;
	private String message;
}
