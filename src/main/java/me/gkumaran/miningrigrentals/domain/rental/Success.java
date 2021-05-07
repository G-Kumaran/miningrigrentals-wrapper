package me.gkumaran.miningrigrentals.domain.rental;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Success
{
	public Long id;
	public Boolean success;
	public String message;
}
