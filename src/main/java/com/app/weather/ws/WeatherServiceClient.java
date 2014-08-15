package com.app.weather.ws;

import com.app.weather.beans.ResponseVO;

public interface WeatherServiceClient {
	public ResponseVO getMethod(String url) throws Exception;
}
