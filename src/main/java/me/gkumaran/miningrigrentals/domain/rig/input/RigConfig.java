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
 * <td>name</td>
 * <td>Y</td>
 * <td>STRING</td>
 * <td></td>
 * <td>Name of rig</td>
 * </tr>
 * <tr>
 * <td>description</td>
 * <td>N</td>
 * <td>STRING/LONGTEXT</td>
 * <td></td>
 * <td>Description of the rig</td>
 * </tr>
 * <tr>
 * <td>status</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td></td>
 * <td>"enabled","disabled"</td>
 * </tr>
 * <tr>
 * <td>server</td>
 * <td>Y</td>
 * <td>STRING</td>
 * <td></td>
 * <td>Server name -- see /info/servers</td>
 * </tr>
 * <tr>
 * <td>price.btc.enabled</td>
 * <td>N</td>
 * <td>BOOLEAN</td>
 * <td>true</td>
 * <td>Enable BTC Pricing</td>
 * </tr>
 * <tr>
 * <td>price.btc.price</td>
 * <td>N</td>
 * <td>DOUBLE/STRING</td>
 * <td></td>
 * <td>Price of the rig per price.type per day (BTC)</td>
 * </tr>
 * <tr>
 * <td>price.btc.autoprice</td>
 * <td>N</td>
 * <td>BOOLEAN</td>
 * <td></td>
 * <td>Enable BTC autopricing</td>
 * </tr>
 * <tr>
 * <td>price.btc.minimum</td>
 * <td>N</td>
 * <td>DOUBLE/STRING</td>
 * <td></td>
 * <td>Minimum price for the autopricer -- 0 to disable</td>
 * </tr>
 * <tr>
 * <td>price.btc.modifier</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td></td>
 * <td>Percent +/- to modify the autopricing (eg: +10 or -5.13 is 10% over or 5.13% under market rates, respectively), 0 to disable</td>
 * </tr>
 * <tr>
 * <td>price.ltc.enabled</td>
 * <td>N</td>
 * <td>BOOLEAN</td>
 * <td>true</td>
 * <td>Enable LTC Pricing</td>
 * </tr>
 * <tr>
 * <td>price.ltc.price</td>
 * <td>N</td>
 * <td>DOUBLE/STRING</td>
 * <td></td>
 * <td>Price of the rig per price.type per day (LTC)</td>
 * </tr>
 * <tr>
 * <td>price.ltc.autoprice</td>
 * <td>N</td>
 * <td>BOOLEAN</td>
 * <td></td>
 * <td>Enable LTC autopricing -- adjusts the LTC rate based on your BTC price and the GDAX market rate</td>
 * </tr>
 * <tr>
 * <td>price.eth.enabled</td>
 * <td>N</td>
 * <td>BOOLEAN</td>
 * <td>true</td>
 * <td>Enable ETH Pricing</td>
 * </tr>
 * <tr>
 * <td>price.eth.price</td>
 * <td>N</td>
 * <td>DOUBLE/STRING</td>
 * <td></td>
 * <td>Price of the rig per price.type per day (ETH)</td>
 * </tr>
 * <tr>
 * <td>price.eth.autoprice</td>
 * <td>N</td>
 * <td>BOOLEAN</td>
 * <td></td>
 * <td>Enable ETH autopricing -- adjusts the ETH rate based on your BTC price and the GDAX market rate</td>
 * </tr>
 * <tr>
 * <td>price.dash.enabled</td>
 * <td>N</td>
 * <td>BOOLEAN</td>
 * <td>true</td>
 * <td>Enable LTC Pricing</td>
 * </tr>
 * <tr>
 * <td>price.dash.price</td>
 * <td>N</td>
 * <td>DOUBLE/STRING</td>
 * <td></td>
 * <td>Price of the rig per price.type per day (DASH)</td>
 * </tr>
 * <tr>
 * <td>price.dash.autoprice</td>
 * <td>N</td>
 * <td>BOOLEAN</td>
 * <td></td>
 * <td>Enable DASH autopricing -- adjusts the DASH rate based on your BTC price and the BITTREX market rate</td>
 * </tr>
 * <tr>
 * <td>price.type</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td>mh</td>
 * <td>The hash type of hash.. defaults to "mh" possible values: [hash,kh,mh,gh,th]</td>
 * </tr>
 * <tr>
 * <td>minhours</td>
 * <td>N</td>
 * <td>FLOAT</td>
 * <td></td>
 * <td>Minimum number of hours available</td>
 * </tr>
 * <tr>
 * <td>maxhours</td>
 * <td>N</td>
 * <td>FLOAT</td>
 * <td></td>
 * <td>Maximum number of hours available</td>
 * </tr>
 * <tr>
 * <td>hash.hash</td>
 * <td>N</td>
 * <td>DOUBLE/STRING</td>
 * <td></td>
 * <td>Amounto f hash to advertise</td>
 * </tr>
 * <tr>
 * <td>hash.type</td>
 * <td>N</td>
 * <td>STRING</td>
 * <td>mh</td>
 * <td>The hash type of hash.. defaults to "mh" possible values: [hash,kh,mh,gh,th]</td>
 * </tr>
 * <tr>
 * <td>suggsted_diff</td>
 * <td>N</td>
 * <td>FLOAT</td>
 * <td></td>
 * <td>Suggested difficulty setting</td>
 * </tr>
 * <tr>
 * <td>ndevices</td>
 * <td>N</td>
 * <td>INT</td>
 * <td></td>
 * <td>Number of devices (workers)</td>
 * </tr>
 * </tbody>
 * </table>
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RigConfig
{
	private Integer id;
	private String name;
	private String description;
	private me.gkumaran.miningrigrentals.constant.STATUS status;
	private String server;
	private price price;
	private BigDecimal minhours;
	private BigDecimal maxhours;
	private hashrate hash;
	private BigDecimal suggested_diff;
	private Integer ndevices;

	@Data
	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class price
	{
		private btc_pricing btc;
		private alt_pricing ltc;
		private alt_pricing eth;
		private alt_pricing dash;
		private alt_pricing bch;
		@NonNull
		private me.gkumaran.miningrigrentals.constant.HASHRATE type;

		@Data
		@Builder
		@JsonInclude(JsonInclude.Include.NON_NULL)
		public static class btc_pricing
		{
			private BigDecimal price;
			private Boolean autoprice;
			private BigDecimal minimum;
			private BigDecimal modifier;
		}

		@Data
		@Builder
		@JsonInclude(JsonInclude.Include.NON_NULL)
		public static class alt_pricing
		{
			private Boolean enabled;
			private BigDecimal price;
			private Boolean autoprice;
		}
	}

	@Data
	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class hashrate
	{
		private BigDecimal hash;
		private me.gkumaran.miningrigrentals.constant.HASHRATE type;
	}
}
