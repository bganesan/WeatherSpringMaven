package com.app.weather.ws;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.weather.beans.ResponseVO;
import com.app.weather.util.HttpClientUtil;
import com.app.weather.weatherresource.WeatherServiceImpl;

/**
* The service client which triggers
* connects to the weather data service
* through the HTTP GET Protocol.  
*
* @author  Balasubramanian
* @version 1.0
* @since   2014-08-14
*/
@Component
public class WeatherServiceClientImpl implements WeatherServiceClient {

	@Autowired
	private HttpClientUtil httpClientUtil;

	public static final String CLASS_NAME = WeatherServiceClientImpl.class
			.getName();
	
	private static final Logger logger = Logger.getLogger(WeatherServiceImpl.class);


	/**
	 * GetMethod implementation to call the weather data service.
	 *
	 * @param  String url value of the URL to be triggered to webservice
	 * @return ResponseVO object contains the response code and response string 
	 */
	@Override
	public ResponseVO getMethod(String url) throws Exception {
		final String METHOD_NAME = "getMethod";
		if(logger.isInfoEnabled()){
			logger.info(CLASS_NAME+":"+METHOD_NAME+": entry ");
		}
		
		//create new GetMethod
		GetMethod getMethod = new GetMethod();
		StringBuffer respBuf = new StringBuffer("");
		BufferedReader br = null;
		try{
			if(logger.isDebugEnabled()){
				logger.debug(CLASS_NAME+":"+METHOD_NAME+": entry ");
			}
			
			//Create URL from the request url string, 
			getMethod.setURI(new URI(url, true));
			if(logger.isInfoEnabled()){
				logger.info(CLASS_NAME+":"+METHOD_NAME+": Before executing service ");
			}
			
			//hit the get request through HttpClientUtil httpclient
			int responseCode = httpClientUtil.getHttpClient().executeMethod(getMethod);
			if(logger.isInfoEnabled()){
				logger.info(CLASS_NAME+":"+METHOD_NAME+": After executing service :"+responseCode);
			}
			
			//create the InputStreamReader for processing the response
			Reader reader = new InputStreamReader(
					getMethod.getResponseBodyAsStream(),
					getMethod.getResponseCharSet());
			br = new BufferedReader(reader);
			
			//loop through the response and build the final response string
			for (String line = null; (line = br.readLine()) != null;) {
				respBuf.append(line);
			}
			if(logger.isDebugEnabled()){
				logger.debug(CLASS_NAME+":"+METHOD_NAME+": Building response ");
			}
			
			//create a new responseVO object with the responseCode and responseString
			ResponseVO responseVO = new ResponseVO(responseCode, respBuf.toString());
			if(logger.isInfoEnabled()){
				logger.info(CLASS_NAME+":"+METHOD_NAME+": Exit :"+responseCode);
			}
			
			//return responseVO object
			return responseVO;
		}
		finally{
			if(logger.isInfoEnabled()){
				logger.info(CLASS_NAME+":"+METHOD_NAME+": Executing finally");
			}
			
			//finally close the bufferedreader and release connection of GetMethod
			if(null != br){
				br.close();
			}
			getMethod.releaseConnection();
		}
	}

}
