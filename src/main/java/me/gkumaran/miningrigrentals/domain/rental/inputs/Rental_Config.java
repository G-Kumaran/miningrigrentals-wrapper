package me.gkumaran.miningrigrentals.domain.rental.inputs;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

/*

Path	Required	Data Type	Default	Info
type	N	STRING	renter	Type is one of [owner,renter] -- owner means rentals on your rigs, renter means rentals you purchased
algo	N	STRING		Filter by algo, see /info/algos
history	N	BOOLEAN	false	true = Show completed rentals, false = Active rentals
rig	N	INT		Show rentals related to a specific rig ID
start	N	INT	0	Start number (for pagination)
limit	N	INT	25	Limit number (for pagination)
currency	N	STRING		Filter by rentals paid currency, one of (BTC,LTC,ETH,DASH)
 */

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rental_Config
{
	public int rig;
	public float length;
	public int profile;
	@Builder.Default
	public String currency = "BTC";
	public rate rate;

	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class rate
	{
		public String type;
		public float price;
	}
}
