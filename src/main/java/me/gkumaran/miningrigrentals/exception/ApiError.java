package me.gkumaran.miningrigrentals.exception;

import lombok.Data;

@Data
public class ApiError
{
	private Integer id;
	private Boolean success;
	private String message;
	private String permission;
}
