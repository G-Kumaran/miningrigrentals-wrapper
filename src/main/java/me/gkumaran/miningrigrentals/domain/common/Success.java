package me.gkumaran.miningrigrentals.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Success
{
	private Long id;
	private Boolean success;
	private String message;
}
