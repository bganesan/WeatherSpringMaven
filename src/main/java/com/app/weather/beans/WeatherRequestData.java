package com.app.weather.beans;

/**
* The WeatherRequestData class to which the 
* input request will be converted for controller
* handling.  
*
* @author  Balasubramanian
* @version 1.0
* @since   2014-08-14 
*/
public class WeatherRequestData {
	
	private String zipCode;

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
