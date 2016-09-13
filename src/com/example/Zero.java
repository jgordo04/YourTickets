package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jamiegordonlipkin on 9/11/16.
 */

@JsonIgnoreProperties({"type","id","test","locale","postalCode","city","state","country","address","_links"})
public class Zero {

    String name;

    public Zero(){

    }

    public Zero(String name){
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
