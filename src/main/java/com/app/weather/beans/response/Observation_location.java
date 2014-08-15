package com.app.weather.beans.response;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize
public class Observation_location
{
    private String full;

    private String elevation;

    private String state;

    private String longitude;

    private String latitude;

    private String country_iso3166;

    private String country;

    private String city;

    public String getFull ()
    {
        return full;
    }

    public void setFull (String full)
    {
        this.full = full;
    }

    public String getElevation ()
    {
        return elevation;
    }

    public void setElevation (String elevation)
    {
        this.elevation = elevation;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getCountry_iso3166 ()
    {
        return country_iso3166;
    }

    public void setCountry_iso3166 (String country_iso3166)
    {
        this.country_iso3166 = country_iso3166;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }
}
