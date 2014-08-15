package com.app.weather.controller;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.weather.InvalidRequestException;
import com.app.weather.beans.ResponseVO;
import com.app.weather.beans.WeatherRequestData;
import com.app.weather.beans.WeatherResponse;
import com.app.weather.beans.response.Weather;
import com.app.weather.controller.base.WeatherController;
import com.app.weather.weatherresource.WeatherService;
import com.google.gson.Gson;

/**
* The controller which handles all the request of 
* Weather Application. The controller handles all 
* the requests for both displaying the weather data 
* request form and respond back with the weather data.  
*
* @author  Balasubramanian
* @version 1.0
* @since   2014-08-14 
*/
@Controller
@RequestMapping
public class WeatherAppController extends WeatherController{
	@Autowired
	private WeatherService weatherService;

	public static final String CLASS_NAME = WeatherAppController.class
			.getName();

	private static final Logger logger = Logger.getLogger(WeatherAppController.class);

	/**
	 * Returns the final view name that will be returned to the 
	 * view layer. This method handles the GET request and 
	 * return the weatherAppDisplay jsp to display the request
	 * form for displaying weather data. 
	 *
	 * @param  empty
	 * @return String view name that will be loaded
	 */
	@RequestMapping(value="/weather", method = RequestMethod.GET)
	public String displayWeather() throws Exception {
		final String METHOD_NAME = "displayWeather";
		
		//logger
		if(logger.isInfoEnabled()){
			logger.info(CLASS_NAME+":"+METHOD_NAME+": display weatherAppDisplay view");
		}
		
		//return weatherAppDisplay.jsp 
		return "weatherAppDisplay";
	}
	
	/**
	 * This method gets the data from the service using the 
	 * input zipcode. The zipcode will have to pass all the validations 
	 * inorder to get the final weather data from service.
	 * 
	 * The weather data from service is parsed using Gson 
	 * and WeatherResponse is built from the service response. 
	 * This WeatherResponse is responded as a JSON back to the request.
	 *
	 * @param  ModelAttribute WeatherRequestData
	 * @param  BindingResult result for input validation
	 * @param  weatherAppUrl value of the service URL prefix from config
	 * @param  weatherAppUrlSuffix value of the service URL suffix from config
	 * @return ResponseBody returns a JSON as response to the request 
	 */
	@RequestMapping(value="/getWeather", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody WeatherResponse getWeather(@ModelAttribute(value="WeatherRequestData") WeatherRequestData weatherRequest, BindingResult result, 
			@Value("${weatherAppUrl}") String url, 
			@Value("${weatherAppUrlSuffix}") String urlSuffix) throws Exception {
		final String METHOD_NAME = "getWeather";

		//logger
		if(logger.isInfoEnabled()){
			logger.info(CLASS_NAME+":"+METHOD_NAME+": entry");
		}
		
		//if the binding result has errors, request has errors
		if(result.hasErrors()){
			throw new InvalidRequestException("_ERR_INVALID_REQ");
		}
		
		//create the final response object
		WeatherResponse weatherResponse = new WeatherResponse(); 

		//logger
		if(logger.isDebugEnabled()){
			logger.debug(CLASS_NAME+":"+METHOD_NAME+": zipcode empty check done");
		}

		//check if zipcode in request is null
		if(null == weatherRequest.getZipCode()){
			if(logger.isDebugEnabled()){
				logger.debug(CLASS_NAME+":"+METHOD_NAME+": zipcode is null");
			}
			//return invalid request, handled by super controller
			throw new InvalidRequestException("_ERR_INVALID_REQ");
		}
		//check if zipcode is not empty
		else if(!StringUtils.hasText(weatherRequest.getZipCode())){
			if(logger.isDebugEnabled()){
				logger.debug(CLASS_NAME+":"+METHOD_NAME+": zipcode do not have any text");
			}
			
			//return invalid request, handled by super controller
			throw new InvalidRequestException("_ERR_INVALID_REQ");
		}
		//check if zipcode has length of 5 digits
		else if(weatherRequest.getZipCode().length() != 5){
			if(logger.isDebugEnabled()){
				logger.debug(CLASS_NAME+":"+METHOD_NAME+": zipcode has length greater than or less than 5");
			}

			//return invalid request, handled by super controller
			throw new InvalidRequestException("_ERR_INVALID_REQ");
		}
		//check if zipcode contains invalid characters
		else if(!NumberUtils.isNumber(weatherRequest.getZipCode())){
			if(logger.isDebugEnabled()){
				logger.debug(CLASS_NAME+":"+METHOD_NAME+": zipcode has non number character");
			}

			//return invalid request, handled by super controller
			throw new InvalidRequestException("_ERR_INVALID_REQ");
		}

		//logger
		if(logger.isInfoEnabled()){
			logger.info(CLASS_NAME+":"+METHOD_NAME+": Validations successful, going to get weather data");
		}

		//get weather response data from the WeatherService
		ResponseVO responseVO = weatherService.getWeatherData(weatherRequest.getZipCode());
		
		//logger
		if(logger.isInfoEnabled()){
			logger.info(CLASS_NAME+":"+METHOD_NAME+": retrieved weather data :"+responseVO);
		}
		
		//check if the response from the service is success, response code will be 200 from service
		if(null != responseVO && HttpStatus.SC_OK == responseVO.getResponseCode()){
			//logger
			if(logger.isDebugEnabled()){
				logger.debug(CLASS_NAME+":"+METHOD_NAME+": weather data service call is success");
			}

			//create a Gson object to convert the response to a JSON object
			Gson gson = new Gson();
			//convert response object to JSON
			Weather weather = gson.fromJson(responseVO.getResponseString(), Weather.class);
			//logger
			if(logger.isDebugEnabled()){
				logger.debug(CLASS_NAME+":"+METHOD_NAME+": building final response");
			}
			//if weather response has error, throw invalid zip, this zipcode is not available in service
			if(null != weather.getResponse() && null != weather.getResponse().getError() && 
					weather.getResponse().getError().getType().equals("querynotfound")){
				
				//logger
				if(logger.isDebugEnabled()){
					logger.debug(CLASS_NAME+":"+METHOD_NAME+": zipcode is not found in service");
				}
				
				//throw error to front end
				throw new InvalidRequestException("_ERR_ZIP_NOTFOUND");
			}
			else{
				//resposne is success, create response object
				weatherResponse.setResponseStatus("SUCCESS");
				weatherResponse.setTemperature(weather.getCurrent_observation().getTemp_f());
				weatherResponse.setCity(weather.getCurrent_observation().getDisplay_location().getCity());
				weatherResponse.setState(weather.getCurrent_observation().getDisplay_location().getState_name());
				weatherResponse.setZip(weather.getCurrent_observation().getDisplay_location().getZip());
				if(logger.isDebugEnabled()){
					logger.debug(CLASS_NAME+":"+METHOD_NAME+": final response data temperature: "+weatherResponse.getTemperature());
					logger.debug(CLASS_NAME+":"+METHOD_NAME+": final response data city: "+weatherResponse.getCity());
					logger.debug(CLASS_NAME+":"+METHOD_NAME+": final response data state: "+weatherResponse.getState());
					logger.debug(CLASS_NAME+":"+METHOD_NAME+": final response data zip: "+weatherResponse.getZip());
				}
			}
			
		}
		else{
			//if weather response has error, throw invalid zip, this zipcode is not available in service
			if(logger.isInfoEnabled()){
				logger.info(CLASS_NAME+":"+METHOD_NAME+": weather data service call failed, responding failure");
			}
			
			//throw error to front end
			throw new InvalidRequestException("_ERR_TEMP_ERROR");
		}



		if(logger.isInfoEnabled()){
			logger.info(CLASS_NAME+":"+METHOD_NAME+": exit");
		}

		//return the JSON response to front end
		return weatherResponse;

	}

}
