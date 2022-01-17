package me.gkumaran.miningrigrentals.domain.rental;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import me.gkumaran.miningrigrentals.deserializer.ZonedDateDeserializer;

@Data
public class Extension
{

	@JsonDeserialize(using = ZonedDateDeserializer.class)
	private ZonedDateTime when;
	private BigDecimal length;
	private me.gkumaran.miningrigrentals.domain.common.Price price;
	private me.gkumaran.miningrigrentals.domain.common.Hashrate hashrate;
	private String credit;
	private String payment;
}
