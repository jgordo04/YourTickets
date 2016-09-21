package com.example;

/**
 * Created by jamiegordonlipkin on 9/19/16.
 */
public class User {

    String firstName;
    String ID;

    public User(){

    }

    public User(String firstName, String ID){
        this.firstName = firstName;
        this.ID = ID;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getID(){
        return ID;
    }

}
