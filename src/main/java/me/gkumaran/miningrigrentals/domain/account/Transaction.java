package me.gkumaran.miningrigrentals.domain.account;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
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
	private String info;
	private Integer rental;
	private Integer rig;
	@JsonProperty("txid")
	private String txId;
	@JsonProperty("payout_address")
	private String payoutAddress;
	@JsonProperty("txfee")
	private BigDecimal txFee;
	private STATUS status;
	@JsonProperty("pending_seconds")
	private Long pendingSeconds;
}
