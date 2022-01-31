package me.gkumaran.miningrigrentals.domain.rental;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;
import me.gkumaran.miningrigrentals.deserializer.ZonedDateDeserializer;

@Data
public class Messages
{
	private Integer rentalid;
	private ArrayList<Message> messages;

	@Data
	public static class Message
	{
		private String username;
		@JsonProperty("is_admin")
		private Boolean isAdmin;
		@JsonProperty("is_support")
		private Boolean isSupport;
		@JsonDeserialize(using = ZonedDateDeserializer.class)
		private ZonedDateTime when;
		private String message;
	}
}
