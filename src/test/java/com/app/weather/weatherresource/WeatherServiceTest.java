package com.app.weather.weatherresource;

import java.net.SocketTimeoutException;


import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.app.weather.beans.ResponseVO;
import com.app.weather.ws.WeatherServiceClient;
import com.app.weather.ws.WeatherServiceClientImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class WeatherServiceTest{

	private static final Logger logger = Logger.getLogger(WeatherServiceTest.class);

	@InjectMocks
	private WeatherServiceImpl weatherService;

	@Spy
	private WeatherServiceClient weatherServiceClient;

	private String successZip = "60169";

	private String failureZip = "32300";

	private String servicePrefix = "http://api.wunderground.com/api/ed044d75b91fb500/conditions/q/";

	private String serviceSuffix = ".json";

	private ResponseVO successResponse = new ResponseVO(200, "{  \"response\": {  \"version\":\"0.1\",  \"termsofService\":\"http://www.wunderground.com/weather/api/d/terms.html\",  \"features\": {  \"conditions\": 1  }	}  ,	\"current_observation\": {		\"image\": {		\"url\":\"http://icons.wxug.com/graphics/wu2/logo_130x80.png\",		\"title\":\"Weather Underground\",		\"link\":\"http://www.wunderground.com\"		},		\"display_location\": {		\"full\":\"Hoffman Estates, IL\",		\"city\":\"Hoffman Estates\",		\"state\":\"IL\",		\"state_name\":\"Illinois\",		\"country\":\"US\",		\"country_iso3166\":\"US\",		\"zip\":\"60169\",		\"magic\":\"1\",		\"wmo\":\"99999\",		\"latitude\":\"42.06779861\",		\"longitude\":\"-88.10109711\",		\"elevation\":\"246.00000000\"		},		\"observation_location\": {		\"full\":\"NW Chicago, IL US, Hoffman Estates, Illinois\",		\"city\":\"NW Chicago, IL US, Hoffman Estates\",		\"state\":\"Illinois\",		\"country\":\"US\",		\"country_iso3166\":\"US\",		\"latitude\":\"42.058300\",		\"longitude\":\"-88.118027\",		\"elevation\":\"628 ft\"		},		\"estimated\": {		},		\"station_id\":\"KILHOFFM1\",		\"observation_time\":\"Last Updated on August 13, 10:25 PM CDT\",		\"observation_time_rfc822\":\"Wed, 13 Aug 2014 22:25:00 -0500\",		\"observation_epoch\":\"1407986700\",		\"local_time_rfc822\":\"Wed, 13 Aug 2014 22:26:18 -0500\",		\"local_epoch\":\"1407986778\",		\"local_tz_short\":\"CDT\",		\"local_tz_long\":\"America/Chicago\",		\"local_tz_offset\":\"-0500\",		\"weather\":\"Clear\",		\"temperature_string\":\"64.5 F (18.1 C)\",		\"temp_f\":64.5,		\"temp_c\":18.1,		\"relative_humidity\":\"71%\",		\"wind_string\":\"Calm\",		\"wind_dir\":\"SSE\",		\"wind_degrees\":148,		\"wind_mph\":0.0,		\"wind_gust_mph\":0,		\"wind_kph\":0,		\"wind_gust_kph\":0,		\"pressure_mb\":\"1017\",		\"pressure_in\":\"30.03\",		\"pressure_trend\":\"-\",		\"dewpoint_string\":\"55 F (13 C)\",		\"dewpoint_f\":55,		\"dewpoint_c\":13,		\"heat_index_string\":\"NA\",		\"heat_index_f\":\"NA\",		\"heat_index_c\":\"NA\",		\"windchill_string\":\"NA\",		\"windchill_f\":\"NA\",		\"windchill_c\":\"NA\",		\"feelslike_string\":\"64.5 F (18.1 C)\",		\"feelslike_f\":\"64.5\",		\"feelslike_c\":\"18.1\",		\"visibility_mi\":\"10.0\",		\"visibility_km\":\"16.1\",		\"solarradiation\":\"--\",		\"UV\":\"0\",\"precip_1hr_string\":\"0.00 in ( 0 mm)\",		\"precip_1hr_in\":\"0.00\",		\"precip_1hr_metric\":\" 0\",		\"precip_today_string\":\"0.00 in (0 mm)\",		\"precip_today_in\":\"0.00\",		\"precip_today_metric\":\"0\",		\"icon\":\"clear\",		\"icon_url\":\"http://icons.wxug.com/i/c/k/nt_clear.gif\",		\"forecast_url\":\"http://www.wunderground.com/US/IL/Hoffman_Estates.html\",		\"history_url\":\"http://www.wunderground.com/weatherstation/WXDailyHistory.asp?ID=KILHOFFM1\",		\"ob_url\":\"http://www.wunderground.com/cgi-bin/findweather/getForecast?query=42.058300,-88.118027\",		\"nowcast\":\"\"	}}");

	private ResponseVO failureResponse = new ResponseVO(200, "{  \"response\": {  \"version\":\"0.1\",  \"termsofService\":\"http://www.wunderground.com/weather/api/d/terms.html\",  \"features\": {  \"conditions\": 1  }		,	\"error\": {		\"type\": \"querynotfound\"		,\"description\": \"No cities match your search query\"	}	}}");

	@Before
	public void setup() throws Exception {
		weatherServiceClient = new WeatherServiceClientImpl();
		weatherService = new WeatherServiceImpl();
		weatherService.setWeatherAppUrl(servicePrefix);
		weatherService.setWeatherAppUrlSuffix(serviceSuffix);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetWeatherData(){
		try {
			Mockito.doReturn(successResponse).when(weatherServiceClient).getMethod(servicePrefix+successZip+serviceSuffix);
			ResponseVO response = weatherService.getWeatherData(successZip);
			Assert.assertNotNull(response);
		} catch (Exception e) {
			logger.error("Exception in test case testGetWeatherData");
		}
	}

	@Test
	public void testGetWeatherDataFailure(){
		try {
			Mockito.doReturn(failureResponse).when(weatherServiceClient).getMethod(servicePrefix+failureZip+serviceSuffix);
			ResponseVO response = weatherService.getWeatherData(failureZip);
			Assert.assertNotNull(response);
		} catch (Exception e) {
			logger.error("Exception in test case testGetWeatherDataFailure");
		}
	}

	@Test(expected = SocketTimeoutException.class)
	public void testGetWeatherDataFailureException() throws Exception{
		Mockito.doThrow(new SocketTimeoutException()).when(weatherServiceClient).getMethod(servicePrefix+failureZip+serviceSuffix);
		weatherService.getWeatherData(failureZip);
	}
}
