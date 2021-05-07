package me.gkumaran.miningrigrentals.domain.rig.input;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtendConfig
{
	private Integer id;
	@Builder.Default
	private Float hours = 0F;
	@Builder.Default
	private Float minutes = 0F;
}
