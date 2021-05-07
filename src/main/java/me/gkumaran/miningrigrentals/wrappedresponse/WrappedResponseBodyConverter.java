package me.gkumaran.miningrigrentals.wrappedresponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

class WrappedResponseBodyConverter<T> implements Converter<ResponseBody, T>
{
	private Converter<ResponseBody, WrappedResponse<T>> converter;

	public WrappedResponseBodyConverter(Converter<ResponseBody, WrappedResponse<T>> converter)
	{
		this.converter = converter;
	}

	@Override
	public T convert(ResponseBody value) throws IOException
	{
		return converter.convert(value)
						.getData();
	}
}