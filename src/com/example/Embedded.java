package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * Created by jamiegordonlipkin on 9/11/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Embedded {

    List<Events> event = new ArrayList<Events>();
    List<Venues> venue = new ArrayList<Venues>();
    //Venues venue = new Venues();
    //Events event = new Events();

    public Embedded(){

    }

    public Embedded(List<Events> event){
        this.event = event;
    }

    @JsonProperty("events")
    public List<Events> getEvents(){
        return event;
    }

    @JsonProperty("events")
    public void setEvents(List<Events> event){
        this.event = event;
    }

    @JsonProperty("venues")
    public List<Venues> getVenue(){
        return venue;
    }

    @JsonProperty("venues")
    public void setVenue(List<Venues> venue){
        this.venue = venue;
    }

}
