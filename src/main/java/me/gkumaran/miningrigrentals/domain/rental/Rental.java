package me.gkumaran.miningrigrentals.domain.rental;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import me.gkumaran.miningrigrentals.deserializer.ZonedDateDeserializer;
import me.gkumaran.miningrigrentals.domain.rig.Rig;

@Data
public class Rental
{
	private Integer id;
	private String owner;
	private String renter;
	private Hash hashrate;
	private Price price;
	@JsonProperty("price_converted")
	private PriceConverted priceConverted;
	private BigDecimal length;
	private BigDecimal extended;
	@JsonDeserialize(using = ZonedDateDeserializer.class)
	private ZonedDateTime start;
	@JsonDeserialize(using = ZonedDateDeserializer.class)
	private ZonedDateTime end;
	private Boolean ended;
	private Rig rig;
	private List<Extension> extensions;

	@Data
	public static class Hash
	{
		private me.gkumaran.miningrigrentals.domain.common.Hashrate advertised;
		private me.gkumaran.miningrigrentals.domain.common.Hashrate average;
	}

	@Data
	public static class Price
	{
		private String type;
		private BigDecimal advertised;
		private BigDecimal paid;
		private String currency;
	}

	@Data
	public static class PriceConverted
	{
		private me.gkumaran.miningrigrentals.constant.HASHRATE type;
		private BigDecimal advertised;
		private String currency;
	}
}
