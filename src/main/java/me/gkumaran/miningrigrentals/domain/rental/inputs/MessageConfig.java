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
 * <td>message</td>
 * <td>Y</td>
 * <td>STRING</td>
 * <td></td>
 * <td>The message to add to a rental.</td>
 * </tr>
 * </tbody>
 * </table>
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageConfig
{
	@NonNull
	private String message;
}
