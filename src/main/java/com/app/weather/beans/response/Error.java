package com.app.weather.beans.response;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize
public class Error {

	private String description;

	private String type;

	public String getDescription ()
	{
		return description;
	}

	public void setDescription (String description)
	{
		this.description = description;
	}

	public String getType ()
	{
		return type;
	}

	public void setType (String type)
	{
		this.type = type;
	}

}
