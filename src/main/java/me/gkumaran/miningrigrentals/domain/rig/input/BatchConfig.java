package me.gkumaran.miningrigrentals.domain.rig.input;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

/**
 * @param <T> Batch of Configs
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BatchConfig<T>
{
	@Singular
	private List<T> rigs;
}
