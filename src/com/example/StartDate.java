package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jamiegordonlipkin on 9/11/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class StartDate {

    String date;

    public StartDate(){

    }

    public StartDate(String date){
        this.date = date;
    }

    @JsonProperty("localDate")
    public String getDate(){
        return date;
    }

    @JsonProperty("localDate")
    public void setDate(String date){
        this.date = date;
    }

}
