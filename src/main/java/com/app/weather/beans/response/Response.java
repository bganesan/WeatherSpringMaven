package com.app.weather.beans.response;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize
public class Response
{
	private Features features;

	private Error error;

	private String termsofService;

	private String version;

	public Features getFeatures ()
	{
		return features;
	}

	public void setFeatures (Features features)
	{
		this.features = features;
	}

	public String getTermsofService ()
	{
		return termsofService;
	}

	public void setTermsofService (String termsofService)
	{
		this.termsofService = termsofService;
	}

	public String getVersion ()
	{
		return version;
	}

	public void setVersion (String version)
	{
		this.version = version;
	}

	/**
	 * @return the error
	 */
	 public Error getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	 public void setError(Error error) {
		 this.error = error;
	 }
}