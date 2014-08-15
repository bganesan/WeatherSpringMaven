package com.app.weather.weatherresource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.weather.beans.ResponseVO;
import com.app.weather.util.HttpClientUtil;
import com.app.weather.ws.WeatherServiceClient;

/**
* The service class which connects to the 
* weather data service through the service
* client to get the required weather data.  
*
* @author  Balasubramanian
* @version 1.0
* @since   2014-08-14 
*/
@Service
public class WeatherServiceImpl implements WeatherService{
	@Autowired
	private HttpClientUtil httpClientUtil;

	@Autowired
	private WeatherServiceClient weatherServiceClient;
	
    private String weatherAppUrl;
	
    private String weatherAppUrlSuffix;
	
	public static final String CLASS_NAME = WeatherServiceImpl.class
			.getName();
	
	private static final Logger logger = Logger.getLogger(WeatherServiceImpl.class);
	
	/**
	 * This method takes the zipcode and get the response 
	 * from the webservice.
	 *
	 * @param  String zipcode value of the input to service
	 * @return ResponseVO object contains the response code and response string 
	 */
	public ResponseVO getWeatherData(String zipCode) throws Exception{
		final String METHOD_NAME = "getWeatherData";
		if(logger.isInfoEnabled()){
			logger.info(CLASS_NAME+":"+METHOD_NAME+": entry ");
		}
		try{
			//build the service request URL
			StringBuffer sb = new StringBuffer();
			sb.append(this.weatherAppUrl);
			sb.append(zipCode);
			sb.append(this.weatherAppUrlSuffix);
			if(logger.isInfoEnabled()){
				logger.info(CLASS_NAME+":"+METHOD_NAME+": URL to service :"+sb.toString());
			}
			
			//call to service to get weather data
			ResponseVO responseVO = weatherServiceClient.getMethod(sb.toString());
			if(logger.isInfoEnabled()){
				logger.info(CLASS_NAME+":"+METHOD_NAME+": response from service :"+responseVO);
			}
			
			//return the response object
			return responseVO;
		}
		catch(Exception e){
			//throws any exception
			logger.error(CLASS_NAME+":"+METHOD_NAME+": Exception in fetching weather data :"+e.getMessage());
			throw e;
		}

	}

	/**
	 * @return the weatherAppUrl
	 */
	public String getWeatherAppUrl() {
		return weatherAppUrl;
	}

	/**
	 * @param weatherAppUrl the weatherAppUrl to set
	 */
	@Value("${weatherAppUrl}")
	public void setWeatherAppUrl(String weatherAppUrl) {
		this.weatherAppUrl = weatherAppUrl;
	}

	/**
	 * @return the weatherAppUrlSuffix
	 */
	public String getWeatherAppUrlSuffix() {
		return weatherAppUrlSuffix;
	}

	/**
	 * @param weatherAppUrlSuffix the weatherAppUrlSuffix to set
	 */
	@Value("${weatherAppUrlSuffix}")
	public void setWeatherAppUrlSuffix(String weatherAppUrlSuffix) {
		this.weatherAppUrlSuffix = weatherAppUrlSuffix;
	}
	

}
