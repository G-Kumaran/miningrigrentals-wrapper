package me.gkumaran.miningrigrentals;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "mrr")
public class ApiProperties
{
	private String baseUrl = "https://www.miningrigrentals.com/api/v2/";
	private String key = "";
	private String secret = "";
}
