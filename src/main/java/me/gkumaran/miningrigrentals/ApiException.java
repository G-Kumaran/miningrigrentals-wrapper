package me.gkumaran.miningrigrentals;

import me.gkumaran.miningrigrentals.exception.ApiError;

public class ApiException extends RuntimeException
{
	private static final long serialVersionUID = 2930730317393028333L;

	private ApiError error;

	public ApiException(ApiError error)
	{
		this.error = error;
	}

	public ApiException(Throwable cause)
	{
		super(cause);
	}

	public ApiException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ApiError getError()
	{
		return error;
	}

	@Override
	public String getMessage()
	{
		if (error != null)
			return "Miningrigrentals API Error : ".concat(error.getMessage());
		else
			return "Miningrigrentals API Error : ".concat(super.getMessage());
	}
}
