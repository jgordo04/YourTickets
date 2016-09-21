package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketmaster.discovery.model.Venue;

import java.util.List;

/**
 * Created by jamiegordonlipkin on 9/11/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Venues {

    String name;

    //@JsonIgnoreProperties({"type","id","test","url","locale","images"})

    public Venues(){

    }

    public Venues(String name){
        this.name = name;
    }

   @JsonProperty("name")
    public String getName(){
        return name;
    }

    @JsonProperty("name")
    public void setName(String name){
        this.name = name;
    }

}
