package me.gkumaran.miningrigrentals.domain.rig.input;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GraphFilter
{
	private Float hours;
	private Boolean deflate;
}
