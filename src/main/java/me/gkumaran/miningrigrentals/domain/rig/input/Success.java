package me.gkumaran.miningrigrentals.domain.rig.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Success
{
	private Boolean deleted;
}
