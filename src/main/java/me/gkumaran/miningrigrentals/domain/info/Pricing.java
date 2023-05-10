package me.gkumaran.miningrigrentals.domain.info;

import java.math.BigDecimal;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Pricing
{
	@JsonProperty("conversion_rates")
	private HashMap<String, BigDecimal> conversionRates;
	@JsonProperty("market_rates")
	private HashMap<String, HashMap<String, String>> marketRates;
}
