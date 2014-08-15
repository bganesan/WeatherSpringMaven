package com.app.weather.weatherresource;

import com.app.weather.beans.ResponseVO;

public interface WeatherService {
	public ResponseVO getWeatherData(String zipCode) throws Exception;

}
