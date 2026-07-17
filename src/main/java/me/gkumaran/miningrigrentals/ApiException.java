package me.gkumaran.miningrigrentals;

import me.gkumaran.miningrigrentals.exception.ApiError;

public class ApiException extends RuntimeException
{
	private static final long serialVersionUID = -8860810213909604845L;

	public ApiException(ApiError apiError)
	{
		super(apiError.getMessage());
	}

	public ApiException(String message)
	{
		super(message);
	}

	public ApiException(Throwable cause)
	{
		super(cause);
	}

	public ApiException(String message, Throwable cause)
	{
		super(message, cause);
	}

	@Override
	public String getMessage()
	{
		return "Miningrigrentals API Error : ".concat(super.getMessage());
	}
}
