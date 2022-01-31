package me.gkumaran.miningrigrentals.deserializer;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ZonedDateDeserializer extends StdDeserializer<ZonedDateTime>
{
	private static final long serialVersionUID = -8399148744461126415L;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

	public ZonedDateDeserializer()
	{
		this(null);
	}

	public ZonedDateDeserializer(Class<?> vc)
	{
		super(vc);
	}

	@Override
	public ZonedDateTime deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException, JsonProcessingException
	{
		String date = jsonparser.getText();
		try
		{
			return ZonedDateTime.parse(date, formatter)
								.withZoneSameInstant(ZoneId.systemDefault());
		} catch (DateTimeParseException e)
		{
			throw new RuntimeException(e);
		}
	}
}