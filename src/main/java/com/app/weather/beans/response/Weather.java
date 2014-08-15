package com.app.weather.beans.response;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize
public class Weather
{
    private Response response;

    private Current_observation current_observation;

    public Response getResponse ()
    {
        return response;
    }

    public void setResponse (Response response)
    {
        this.response = response;
    }

    public Current_observation getCurrent_observation ()
    {
        return current_observation;
    }

    public void setCurrent_observation (Current_observation current_observation)
    {
        this.current_observation = current_observation;
    }
}