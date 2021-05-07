package me.gkumaran.miningrigrentals.deserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateDeserializer extends StdDeserializer<ZonedDateTime>
{
	private static final long serialVersionUID = -4490136474894219198L;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public LocalDateDeserializer()
	{
		this(null);
	}

	public LocalDateDeserializer(Class<?> vc)
	{
		super(vc);
	}

	@Override
	public ZonedDateTime deserialize(JsonParser jsonparser,
			DeserializationContext context) throws IOException,
			JsonProcessingException
	{
		String date = jsonparser.getText();
		try
		{
			return LocalDateTime.parse(date, formatter)
								.atZone(ZoneId.of("UTC"))
								.withZoneSameInstant(ZoneId.systemDefault());
		} catch (DateTimeParseException e)
		{
			throw new RuntimeException(e);
		}
	}
}