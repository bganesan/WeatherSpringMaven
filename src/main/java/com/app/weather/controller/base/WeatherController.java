package com.app.weather.controller.base;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.app.weather.beans.WeatherError;

/**
* The base controller which handles all the error
* handling for the implemented controllers This builds
* the final JSON error response with the error code
* from the exception.  
*
* @author  Balasubramanian
* @version 1.0
* @since   2014-08-14  
*/
public class WeatherController {

	/**
	 * Returns the exception as a error
	 * JSON object. This method converts 
	 * the exception to a JSON object.
	 * ExceptionHandler annotation will ensure
	 * request is transformed to this controller on any exception.
	 * 
	 * @param  Exception ex
	 * @return JSON object built from WeatherError class
	 */
	@ExceptionHandler (Exception.class)
	public ModelAndView handleError(Exception ex) {
		//return the object to be converted to json
		return new WeatherError(ex.getMessage()).asModelAndView();	}

}
