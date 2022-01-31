package me.gkumaran.miningrigrentals.domain.rig.input;

import java.math.BigDecimal;

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
 * <td>type</td>
 * <td>Y</td>
 * <td>STRING</td>
 * <td></td>
 * <td>Rig type, eg: sha256, scrypt, x11, etc</td>
 * </tr>
 * <tr>
 * <td>minhours</td>
 * <td></td>
 * <td></td>
 * <td></td>
 * <td>Filter the minmum hours of the rig</td>
 * </tr>
 * <tr>
 * <td>minhours.min</td>
 * <td>N</td>
 * <td>INT</td>
 * <td></td>
 * <td>Minimum value to filter</td>
 * </tr>
 * <tr>
 * <td>minhours.max</td>
 * <td>N</td>
 * <td>INT</td>
 * <td></td>
 * <td>Maximum value to filter</td>
 * </tr>
 * <tr>
 * <td>maxhours</td>
 * <td></td>
 * <td></td>
 * <td></td>
 * <td>Filter the maximum hours of the rig</td>
 * </tr>
 * <tr>
 * <td>maxhours.min</td>
 * <td>N</td>
 * <td>INT</td>
 * <td></td>
 * <td>Minimum value to filter</td>
 * </tr>
 * <tr>
 * <td>maxhours.max</td>
 * <td>N</td>
 * <td>INT</td>
 * <td></td>
 * <td>Maximum value to filter</td>
 * </tr>
 * <tr>
 * <td>rpi</td>
 * <td></td>
 * <td></td>
 * <td></td>
 * <td>Filter the RPI score</td>
 * </tr>
 * <tr>
 * <td>rpi.min</td>
 * <td>N</td>
 * <td>INT</td>
 * <td></td>
 * <td>Minimum value to filter</td>
 * </tr>
 * <tr>
 * <td>rpi.max</td>
 * <td>N</td>
 * <td>INT</td>
 * <td></td>
 * <td>Maximum value to filter</td>
 * </tr>
 * <tr>
 * <td>hash</td>
 * <td></td>
 * <td></td>
 * <td></td>
 * <td>Filter the hashrate</td>
 * </tr>
 * <tr>
 * <td>hash.min</td>
 * <td>N</td>
 * <td>INT</td>
 * <td></td>
 * <td>Minimum value to filter</td>
 * </tr>
 * <tr>
 * <td>hash.max</td>
 * <td>N</td>
 * <td>INT</td>
 * <td></td>
 * <td>Maximum value to filter</td>
 * </tr>
 * <tr>
 * <td>hash.type</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td></td>
 * <td>The hash type of min/max. defaults to "mh", possible values: [hash,kh,mh,gh,th]</td>
 * </tr>
 * <tr>
 * <td>price</td>
 * <td></td>
 * <td></td>
 * <td></td>
 * <td>Filter the price</td>
 * </tr>
 * <tr>
 * <td>price.min</td>
 * <td>N</td>
 * <td>INT</td>
 * <td></td>
 * <td>Minimum value to filter</td>
 * </tr>
 * <tr>
 * <td>price.max</td>
 * <td>N</td>
 * <td>INT</td>
 * <td></td>
 * <td>Maximum value to filter</td>
 * </tr>
 * <tr>
 * <td>offline</td>
 * <td>N</td>
 * <td>BOOLEAN</td>
 * <td>false</td>
 * <td>To show or not to show offline rigs</td>
 * </tr>
 * <tr>
 * <td>rented</td>
 * <td>N</td>
 * <td>BOOLEAN</td>
 * <td>false</td>
 * <td>to show or not to show rented rigs</td>
 * </tr>
 * <tr>
 * <td>region</td>
 * <td></td>
 * <td></td>
 * <td></td>
 * <td>Filter the region</td>
 * </tr>
 * <tr>
 * <td>region.type</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td></td>
 * <td>Determines if this filter is an inclusive or exclusive filter.. possible options are [include,exclude]</td>
 * </tr>
 * <tr>
 * <td>region.[REGION]</td>
 * <td>N</td>
 * <td>BOOLEAN</td>
 * <td></td>
 * <td>A region to include/exclude</td>
 * </tr>
 * <tr>
 * <td>count</td>
 * <td>N</td>
 * <td>INT</td>
 * <td>100</td>
 * <td>Number of results to return, max is 100</td>
 * </tr>
 * <tr>
 * <td>offset</td>
 * <td>N</td>
 * <td>INT</td>
 * <td>0</td>
 * <td>What result number to start with, returning COUNT results</td>
 * </tr>
 * <tr>
 * <td>orderby</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td>"score"</td>
 * <td>Field to order the results by. Default is "score", Possible values: [price,hashrate,minhrs,maxhrs,rpi,name,region,online,rented]</td>
 * </tr>
 * <tr>
 * <td>orderdir</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td>"asc"</td>
 * <td>Order direction</td>
 * </tr>
 * </tbody>
 * </table>
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RigFilter
{
	@NonNull
	private String type;
	private range minhours;
	private range maxhours;
	private range rpi;
	private hrrange hash;
	private range price;
	@Builder.Default
	private Boolean offline = false;
	@Builder.Default
	private Boolean rented = false;
	private region region;
	@Builder.Default
	private Integer count = 100;
	@Builder.Default
	private Integer offset = 0;
	@Builder.Default
	private ORDERBY orderby = ORDERBY.score;
	@Builder.Default
	private ORDERDIR orderdir = ORDERDIR.asc;

	@Data
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class range
	{
		private BigDecimal min;
		private BigDecimal max;
	}

	@Data
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class hrrange
	{
		private BigDecimal min;
		private BigDecimal max;
		private me.gkumaran.miningrigrentals.constant.HASHRATE type;
	}

	@Data
	public static class region
	{
		private REGIONTYPE type;

		private enum REGIONTYPE
		{
			include,
			exclude;
		}
	}

	public enum ORDERBY
	{
		rpi,
		hash,
		price,
		minhrs,
		maxhrs,
		score;
	}

	public enum ORDERDIR
	{
		asc,
		dsc;
	}
}
