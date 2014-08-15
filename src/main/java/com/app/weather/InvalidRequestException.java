package com.app.weather;

/**
* The custom exception class to handle
* the invalid request from front end. 
* This is used by the WeatherController to 
* throw back error to front end.  
*
* @author  Balasubramanian
* @version 1.0
* @since   2014-08-14
*/
public class InvalidRequestException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public InvalidRequestException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidRequestException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidRequestException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
