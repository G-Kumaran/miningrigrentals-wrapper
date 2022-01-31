package me.gkumaran.miningrigrentals.domain.rental.inputs;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import me.gkumaran.miningrigrentals.constant.TYPE;

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
 * <td>type</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td>renter</td>
 * <td>Type is one of [owner,renter] -- owner means rentals on your rigs, renter means rentals you purchased</td>
 * </tr>
 * <tr>
 * <td>algo</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td></td>
 * <td>Filter by algo, see /info/algos</td>
 * </tr>
 * <tr>
 * <td>history</td>
 * <td>N</td>
 * <td>BOOLEAN</td>
 * <td>false</td>
 * <td>true = Show completed rentals, false = Active rentals</td>
 * </tr>
 * <tr>
 * <td>rig</td>
 * <td>N</td>
 * <td>INT</td>
 * <td></td>
 * <td>Show rentals related to a specific rig ID</td>
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
 * <td>25</td>
 * <td>Limit number (for pagination)</td>
 * </tr>
 * <tr>
 * <td>currency</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td></td>
 * <td>Filter by rentals paid currency, one of (BTC,LTC,ETH,DASH)</td>
 * </tr>
 * </tbody>
 * </table>
 */
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
