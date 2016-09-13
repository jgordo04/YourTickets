package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketmaster.discovery.model.Venue;

import java.util.List;

/**
 * Created by jamiegordonlipkin on 9/11/16.
 */

@JsonIgnoreProperties({"type","id","test","locale","postalCode","city","state","country","address","_links"})
public class Venues {

    //Zero venueZero = new Zero();
    String name;

    //@JsonIgnoreProperties({"type","id","test","url","locale","images"})

    public Venues(){

    }

    public Venues(String name){
        this.name = name;
    }

    /*(Zero venueZero ){
        this.venueZero = venueZero;
    }

    @JsonProperty("0")
    public Zero getZero(){
        return venueZero;
    }

    @JsonProperty("0")
    public void setVenue(Zero venueZero){
        this.venueZero = venueZero;
    }   */

    @JsonProperty("name")
    public String getName(){
        return name;
    }

    @JsonProperty("name")
    public void setName(String name){
        this.name = name;
    }

}
