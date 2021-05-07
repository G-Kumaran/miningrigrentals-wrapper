package me.gkumaran.miningrigrentals.wrappedresponse;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class UnwrapConverterFactory extends Converter.Factory
{
	private JacksonConverterFactory factory;

	public UnwrapConverterFactory(JacksonConverterFactory factory)
	{
		this.factory = factory;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Converter<ResponseBody, ?> responseBodyConverter(final Type type,
			Annotation[] annotations,
			Retrofit retrofit)
	{
		// e.g. WrappedResponse<Person>
		Type wrappedType = new ParameterizedType()
		{
			@Override
			public Type[] getActualTypeArguments()
			{
				// -> WrappedResponse<type>
				return new Type[] { type };
			}

			@Override
			public Type getOwnerType()
			{
				return null;
			}

			@Override
			public Type getRawType()
			{
				return WrappedResponse.class;
			}
		};
		Converter<ResponseBody, ?> jacksonConverter = factory.responseBodyConverter(wrappedType, annotations, retrofit);
		return new WrappedResponseBodyConverter(jacksonConverter);
	}
}
