package com.app.weather.beans;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
* The weather error class which is used 
* in the error handling within the application.  
*
* @author  Balasubramanian
* @version 1.0
* @since   2014-08-14 
*/
public class WeatherError {
	private String message;
	public WeatherError(String message) {
		this.message = message;
	}

	/**
	 * Returns the view with the object that
	 * will be converted to the error JSON 
	 * object to be returned to front end.  
	 * 
	 * @param  empty
	 * @return ModelAndView which is converted to JSON
	 */
	public ModelAndView asModelAndView() {
		//create jsonview for building response object
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
		Map<String, String> errorMap = new HashMap<String, String>();
		//add message to the resposne object
		errorMap.put("message", message);
		return new ModelAndView(jsonView, "error", message);
	}

}
