package me.gkumaran.miningrigrentals.deserializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonFormat;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@JacksonAnnotationsInside
@JsonFormat(shape = JsonFormat.Shape.STRING,
			pattern = "yyyy-MM-dd HH:mm:ss z")
public @interface ZonedDateDeserializer
{}