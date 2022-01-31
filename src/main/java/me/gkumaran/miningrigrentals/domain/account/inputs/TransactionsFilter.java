package me.gkumaran.miningrigrentals.domain.account.inputs;

import lombok.Builder;
import lombok.Data;
import me.gkumaran.miningrigrentals.domain.account.TYPE;

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
 * <td>start</td>
 * <td>N</td>
 * <td>INT</td>
 * <td>0</td>
 * <td>Start number (for pagination)</td>
 * </tr>
 * <tr>
 * <td>limit</td>
 * <td>N</td>
 * <td>INT</td>
 * <td>100</td>
 * <td>Limit number (for pagination)</td>
 * </tr>
 * <tr>
 * <td>algo</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td></td>
 * <td>Algo to filter -- see /info/algos</td>
 * </tr>
 * <tr>
 * <td>type</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td></td>
 * <td>Type to filter -- one of ['credit','payout','referral','deposit','payment','credit/refund','debit/refund','rental fee']</td>
 * </tr>
 * <tr>
 * <td>rig</td>
 * <td>N</td>
 * <td>INT</td>
 * <td></td>
 * <td>Filter to specific rig.</td>
 * </tr>
 * <tr>
 * <td>rental</td>
 * <td>N</td>
 * <td>INT</td>
 * <td></td>
 * <td>Filter to specific rental.</td>
 * </tr>
 * <tr>
 * <td>txid</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td></td>
 * <td>Filter to specific txid.</td>
 * </tr>
 * <tr>
 * <td>time_greater_eq</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td></td>
 * <td>Filter to greater then or equal, as Unix Timestamp integer.</td>
 * </tr>
 * <tr>
 * <td>time_less_eq</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td></td>
 * <td>Filter to less then or equal, as Unix Timestamp integer.</td>
 * </tr>
 * </tbody>
 * </table>
 */
@Data
@Builder
public class TransactionsFilter
{
	private Integer start;
	private Integer limit;
	private String algo;
	private TYPE type;
	private Integer rental;
	private Integer rig;
	private String txid;
	private Long time_greater_eq;
	private Long time_less_eq;
}
