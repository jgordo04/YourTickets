package com.example;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * Created by jamiegordonlipkin on 9/11/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Events {

    String name;
    Dates date = new Dates();
    Embedded event = new Embedded();

    //@JsonIgnoreProperties({"type","id","test","url","locale","images","sales","classifications","_links"})

    public Events(){

    }

    public Events(String name){
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

    @JsonProperty("dates")
    public Dates getDate(){
        return date;
    }

    @JsonProperty("dates")
    public void setDate(Dates date){
        this.date = date;
    }

    @JsonProperty("_embedded")
    public Embedded getEmbedded(){
        return event;
    }

    @JsonProperty("_embedded")
    public void setEmbedded( Embedded event){
        this.event = event;
    }


}
