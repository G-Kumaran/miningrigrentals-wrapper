package me.gkumaran.miningrigrentals.domain.rig.input;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

/**
 * <table class="table table-striped">
 * <tbody>
 * <tr>
 * <th>Path</th>
 * <th>Required</th>
 * <th>Data Type</th>
 * <th>Default</th>
 * <th>Info</th>
 * </tr>
 * <tr>
 * <td>hours</td>
 * <td>N</td>
 * <td>FLOAT</td>
 * <td></td>
 * <td>Hours to extend by</td>
 * </tr>
 * <tr>
 * <td>minutes</td>
 * <td>N</td>
 * <td>FLOAT</td>
 * <td></td>
 * <td>Minutes to extend by</td>
 * </tr>
 * </tbody>
 * </table>
 */
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
