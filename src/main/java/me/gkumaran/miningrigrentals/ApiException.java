package me.gkumaran.miningrigrentals;

public class ApiException extends RuntimeException
{
	private static final long serialVersionUID = -8860810213909604845L;

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
