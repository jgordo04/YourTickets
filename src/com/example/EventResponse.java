package com.example;

/**
 * Created by jamiegordonlipkin on 9/11/16.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"_links","page"})

public class EventResponse {

    Embedded event = new Embedded();
    //Events event = new Events();
    //Dates date = new Dates();
    //Venues venue = new Venues();

    public EventResponse(){

    }

    public EventResponse(Embedded event){
        this.event = event;
    }

    @JsonProperty("_embedded")
    public Embedded getEmbedded(){
        return event;
    }

    @JsonProperty("_embedded")
    public void setEmbedded( Embedded event){
        this.event = event;
    }

    /*public String getEvent(){
        return event.getName();
    }

    public void setEvent(Events event){
        this.event = event;
    }*/

    /*public String getDate(){
        return date.getDate();
    }

    public void setDate(Dates date){
        this.date = date;
    }*/

    /*public String getVenue(){
        return venue.getVenue();
    }

    public void setVenue(Venues venue){
        this.venue = venue;
    }*/

}
