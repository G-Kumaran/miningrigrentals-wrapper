package me.gkumaran.miningrigrentals.domain.rig.input;

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
 * <td>host</td>
 * <td>Y</td>
 * <td>STRING</td>
 * <td></td>
 * <td>pool host (the part after stratum+tcp://)</td>
 * </tr>
 * <tr>
 * <td>port</td>
 * <td>Y</td>
 * <td>INT</td>
 * <td></td>
 * <td>pool port (ex: 3333)</td>
 * </tr>
 * <tr>
 * <td>user</td>
 * <td>Y</td>
 * <td>STRING</td>
 * <td></td>
 * <td>workername</td>
 * </tr>
 * <tr>
 * <td>pass</td>
 * <td>Y</td>
 * <td>STRING</td>
 * <td></td>
 * <td>worker password</td>
 * </tr>
 * <tr>
 * <td>priority</td>
 * <td>N</td>
 * <td>INT</td>
 * <td></td>
 * <td>0-4 -- can be passed in after pool/ instead.<br>
 * eg /rig/17/pool/0</td>
 * </tr>
 * </tbody>
 * </table>
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PoolConfig
{
	@NonNull
	private Integer priority;
	@NonNull
	private String host;
	@NonNull
	private Integer port;
	@NonNull
	private String user;
	@NonNull
	private String pass;
}