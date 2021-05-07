package me.gkumaran.miningrigrentals.domain.account;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import me.gkumaran.miningrigrentals.deserializer.LocalDateDeserializer;

@Data
public class Transaction
{
	private Integer id;
	private TYPE type;
	private String currency;
	private BigDecimal amount;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private ZonedDateTime when;
	private Integer rental;
	private Integer rig;
	private String txid;
	private STATUS status;
	private Long pending_seconds;
}
