package me.gkumaran.miningrigrentals.domain.riggroup.inputs;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiggroupConfig
{
	@NonNull
	public String name;
	public Boolean enabled;
	public int rental_limit;
}
