package me.gkumaran.miningrigrentals.domain.rental.inputs;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

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
 * <td>length</td>
 * <td>Y</td>
 * <td>FLOAT</td>
 * <td></td>
 * <td>Length in hours to purchase an extension for.</td>
 * </tr>
 * <tr>
 * <td>getcost</td>
 * <td>N</td>
 * <td>ANY</td>
 * <td></td>
 * <td>If defined, the API will simulate the extension.</td>
 * </tr>
 * </tbody>
 * </table>
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExtendConfig
{
	@NonNull
	private Float length;
	@Builder.Default
	private Boolean getcost = false;
}
