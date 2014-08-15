package com.app.weather.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Value;


public class HttpClientUtil {
	private HttpClient httpClient = new HttpClient();

	@Value("${DEFAULT_CONNECTION_TIME_OUT_IN_MS}")
    private String defaultConnectionTimeout;
	
	@Value("${DEFAULT_TOTAL_MAX_CONNECTIONS}")
    private String defaultMaxConnections;
	
	@Value("${DEFAULT_MAX_CONNECTIONS_PER_HOST}")
    private String defaultMaxConnectionsPerHost;
	
	@Value("${WEATHER_APP_CONNECTION_TIMEOUT}")
    private String weatherAppConnectionTimeout;
	
	@Value("${WEATHER_APP_SOCKET_TIMEOUT}")
    private String weatherAppSocketTimeout;
	
	public HttpClient getHttpClient() {
		
		MultiThreadedHttpConnectionManager connMgr = new MultiThreadedHttpConnectionManager();
		DefaultHttpMethodRetryHandler retryHandler = new DefaultHttpMethodRetryHandler(
				0, false);

		connMgr.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				retryHandler);
		connMgr.getParams().setConnectionTimeout(
				Integer.parseInt(defaultConnectionTimeout));
		connMgr.getParams().setDefaultMaxConnectionsPerHost(
				Integer.parseInt(defaultMaxConnectionsPerHost));
		connMgr.getParams().setMaxTotalConnections(
				Integer.parseInt(defaultMaxConnections));
		connMgr.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				retryHandler);
		
		httpClient = new HttpClient(connMgr);
		httpClient
				.getParams()
				.setConnectionManagerTimeout(
						Long.parseLong(weatherAppConnectionTimeout));
		httpClient.getParams().setSoTimeout(
				Integer.parseInt(weatherAppSocketTimeout));
		
		return httpClient;
	}
}
