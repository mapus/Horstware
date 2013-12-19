package com.nttdata.emea.devschool.vehicleordering.network;

public interface OnRestResponse {
	public abstract void onResponse(String response);
	public abstract void onError(Integer errorCode, String errorMessage);
}