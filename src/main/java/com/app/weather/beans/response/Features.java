package com.app.weather.beans.response;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize
public class Features
{
    private String conditions;

    public String getConditions ()
    {
        return conditions;
    }

    public void setConditions (String conditions)
    {
        this.conditions = conditions;
    }
}