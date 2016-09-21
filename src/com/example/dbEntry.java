package com.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by jamiegordonlipkin on 9/19/16.
 */
public class dbEntry {

    public boolean addUser(User user){

        try{
            String myUrl = "jdbc:mariadb://localhost:3306/YourTickets";
            String myUser = "tixAdmin";
            String myPW = "tixAdmin";
            Connection conn = DriverManager.getConnection(myUrl,myUser,myPW);
            Statement stmt = conn.createStatement();
            // the mysql insert statement
            String query = "insert into Users (firstName, ID)"
                    + " values (?, ?)";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, user.getFirstName());
            preparedStmt.setString (2, user.getID());

            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();

            return true;

        }catch(Exception e){
            System.out.println(e);
            return false;
        }

    }

    public String getName(String ID){

        try{
            String myUrl = "jdbc:mariadb://localhost:3306/YourTickets";
            String myUser = "tixAdmin";
            String myPW = "tixAdmin";
            Connection conn = DriverManager.getConnection(myUrl,myUser,myPW);
            Statement stmt = conn.createStatement();
            // the mysql insert statement

            String query = "select firstName from Users where ID=?";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, ID);
            // execute the preparedstatement
            String firstName = "Not set";
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                firstName = rs.getString("firstName");
            }

            conn.close();

            return firstName;

        }catch(Exception e){
            System.out.println(e);
            return "Not set. Database error.";
        }

    }

    public int getSerialID(String ID){

        try{
            String myUrl = "jdbc:mariadb://localhost:3306/YourTickets";
            String myUser = "tixAdmin";
            String myPW = "tixAdmin";
            Connection conn = DriverManager.getConnection(myUrl,myUser,myPW);
            Statement stmt = conn.createStatement();
            // the mysql insert statement

            String query = "select serialID from Users where ID=?";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, ID);
            // execute the preparedstatement
            int serialID = 0;
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                serialID = rs.getInt("serialID");
            }

            conn.close();

            return serialID;

        }catch(Exception e){
            System.out.println(e);
            return 0;
        }

    }

    public boolean addKeyword(int serialID, String keyword){

        try{
            String myUrl = "jdbc:mariadb://localhost:3306/YourTickets";
            String myUser = "tixAdmin";
            String myPW = "tixAdmin";
            Connection conn = DriverManager.getConnection(myUrl,myUser,myPW);
            Statement stmt = conn.createStatement();
            // the mysql insert statement
            String query = "insert into Keywords (serialID, keyword)"
                    + " values (?, ?)";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, serialID);
            preparedStmt.setString (2, keyword);

            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();

            return true;

        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public List<String> getKeywords(int serialID){

        try{
            String myUrl = "jdbc:mariadb://localhost:3306/YourTickets";
            String myUser = "tixAdmin";
            String myPW = "tixAdmin";
            Connection conn = DriverManager.getConnection(myUrl,myUser,myPW);
            Statement stmt = conn.createStatement();
            // the mysql insert statement
            String query = "select keyword from Keywords where serialID=?";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, serialID);

            // execute the preparedstatement
            List<String> keywords = new ArrayList<String>();
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                keywords.add(rs.getString("keyword"));
            }

            conn.close();

            return keywords;

        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public boolean addState(int serialID, String state){

        try{
            String myUrl = "jdbc:mariadb://localhost:3306/YourTickets";
            String myUser = "tixAdmin";
            String myPW = "tixAdmin";
            Connection conn = DriverManager.getConnection(myUrl,myUser,myPW);
            Statement stmt = conn.createStatement();
            // the mysql insert statement
            String query = "insert into States (serialID, state)"
                    + " values (?, ?)";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, serialID);
            preparedStmt.setString (2, state);

            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();

            return true;

        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public List<String> getState(int serialID){

        try{
            String myUrl = "jdbc:mariadb://localhost:3306/YourTickets";
            String myUser = "tixAdmin";
            String myPW = "tixAdmin";
            Connection conn = DriverManager.getConnection(myUrl,myUser,myPW);
            Statement stmt = conn.createStatement();
            // the mysql insert statement
            String query = "select state from States where serialID=?";
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt (1, serialID);

            // execute the preparedstatement
            List<String> states = new ArrayList<String>();
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                states.add(rs.getString("state"));
            }

            conn.close();

            return states;

        }catch(Exception e){
            System.out.println(e);
            return null;
        }

    }





}
