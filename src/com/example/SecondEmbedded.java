package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamiegordonlipkin on 9/11/16.
 */

@JsonIgnoreProperties({"attractions"})
public class SecondEmbedded {

    List<Venues> venue = new ArrayList<Venues>();
    //Venues venue = new Venues();

    public SecondEmbedded(){

    }

    public SecondEmbedded(List<Venues> venue){
        this.venue = venue;
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
