package me.gkumaran.miningrigrentals.wrappedresponse;

import lombok.Data;

@Data
class WrappedResponse<T>
{
	private Boolean success;
	private T data;
}
