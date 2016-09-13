package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * Created by jamiegordonlipkin on 9/11/16.
 */

public class Embedded {

    List<Events> event = new ArrayList<Events>();
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


}
