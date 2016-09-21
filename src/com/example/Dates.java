package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jamiegordonlipkin on 9/11/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dates {

    StartDate date;

    //@JsonIgnoreProperties({"type","id","test","url","locale","images"})

    public Dates(){

    }

    public Dates(StartDate date){
        this.date = date;
    }

    @JsonProperty("start")
    public StartDate getStartDate(){
        return date;
    }

    @JsonProperty("start")
    public void setStartDate(StartDate localDate){
        this.date = localDate;
    }

}
