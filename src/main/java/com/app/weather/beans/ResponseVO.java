package com.app.weather.beans;

/**
* The ResponseVO object which will be received
* from the weather data webservice call.  
*
* @author  Balasubramanian
* @version 1.0
* @since   2014-08-14 
*/
public class ResponseVO {
	private int responseCode;
	private String responseString;
	
	public ResponseVO(int responseCode, String responseString) {
		super();
		this.responseCode = responseCode;
		this.responseString = responseString;
	}
	/**
	 * @return the responseCode
	 */
	public int getResponseCode() {
		return responseCode;
	}
	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	/**
	 * @return the responseString
	 */
	public String getResponseString() {
		return responseString;
	}
	/**
	 * @param responseString the responseString to set
	 */
	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
}
