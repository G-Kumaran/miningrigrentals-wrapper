package me.gkumaran.miningrigrentals.domain.rental.inputs;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import me.gkumaran.miningrigrentals.constant.TYPE;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentalFilter
{
	@Builder.Default
	private TYPE type = TYPE.RENTER;
	private String algo;
	@Builder.Default
	private Boolean history = false;
	private Integer rig;
	@Builder.Default
	private Integer start = 0;
	@Builder.Default
	private Integer limit = 25;
	private String currency;
}
