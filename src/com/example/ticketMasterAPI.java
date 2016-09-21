package com.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.ticketmaster.api.discovery.DiscoveryApi;
import java.io.*;
import java.util.*;
import java.net.*;
import javax.swing.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Created by jamiegordonlipkin on 9/8/16.
 */
public class ticketMasterAPI {

    public static EventResponse httpGet(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        if (conn.getResponseCode() != 200) {
            throw new IOException(conn.getResponseMessage());
        }

        // Buffer the result into a string
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        conn.disconnect();

        ObjectMapper mapper = new ObjectMapper();

        EventResponse results = mapper.readValue(sb.toString(),EventResponse.class);

        return results;

    }

    public static String queryAPI(List<String> keywords_all, List<String> states_all){

        //String [] keywords = keywords_all.split(",");
        String apikey = "uQ2nE4Pd9KYfdZSKsh6Knv5YCqaSJe0T";
        DiscoveryApi api = new DiscoveryApi(apikey);
        String dateString = "";
        String venueString = "";
        String nameString = "";
        String resultsString = "";

        for ( int j = 0; j < keywords_all.size(); j++ ) {
            for ( int m = 0; m < states_all.size(); m++) {
                String keyword = keywords_all.get(j);
                String uri =
                        "https://app.ticketmaster.com/discovery/v2/events.json?keyword=" + keyword +
                                "&stateCode=" + states_all.get(m) +
                                "&countryCode=US&apikey=" + apikey;

                EventResponse result = new EventResponse();
                try {
                    result = httpGet(uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("----- " + keyword + " -----");
                for (int k = 0; k < result.getEmbedded().getEvents().size(); k++) {
                    nameString = result.getEmbedded().getEvents().get(k).getName();
                    dateString = result.getEmbedded().getEvents().get(k).getDate().getStartDate().getDate();
                    venueString = result.getEmbedded().getEvents().get(k).getEmbedded().getVenue().get(0).getName();
                    resultsString += nameString + ": " + venueString + " - " + dateString + "\n";

                /*
                String[] resultsArray = {nameString, venueString, dateString};
                JFrame guiFrame = new JFrame();  //make sure the program exits when the frame closes
                guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                guiFrame.setTitle("Results");
                guiFrame.setSize(300, 250);  //This will center the JFrame in the middle of the screen
                guiFrame.setLocationRelativeTo(null);

                final JPanel comboPanel = new JPanel();
                JLabel comboLbl = new JLabel(keywords[j] + ":");

                comboPanel.add(comboLbl);
                for (int i = 0; i < resultsArray.length; i++) {
                    JLabel resultLabel = new JLabel(resultsArray[i]);
                    comboPanel.add(resultLabel);
                }
                comboPanel.setVisible(true);
                guiFrame.add(comboPanel);
                guiFrame.setVisible(true);
                */
                }
            }
        }

        return resultsString;

    }

    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        System.out.print("Please enter your user ID. If you don't have one, enter 1 to create one.: ");
        String userID = stdin.nextLine();
        if ( userID.equals("1") ) {
            System.out.println("Please enter your first name: ");
            String firstName = stdin.nextLine();
            System.out.println("Please enter your preferred user ID: ");
            String ID = stdin.nextLine();
            User newUser = new User(firstName,ID);
            dbEntry newEntry = new dbEntry();
            boolean success = newEntry.addUser(newUser);
            if ( success ){
                System.out.println("User successfully created.");
            }
        }
        else {
            dbEntry queryEntry = new dbEntry();
            String firstName = queryEntry.getName(userID);
            if ( !firstName.contains("Not set") ) {
                System.out.println("Welcome " + userID + "!\n" +
                        "Would you like to:\n" +
                        "1. Query for concerts with your keywords?\n" +
                        "2. Add a keyword to your list of keywords?\n" +
                        "3. Add a State to search?\n" +
                        "Please enter the corresponding number for your choice\n");
                String option = stdin.nextLine();
                switch (Integer.parseInt(option)) {
                    case 1: {
                        int serialID = queryEntry.getSerialID(userID);
                        List<String> keywords = queryEntry.getKeywords(serialID);
                        List<String> state = queryEntry.getState(serialID);
                        String results = queryAPI(keywords, state);
                        System.out.println(results);
                        break;
                    }
                    case 2: {
                        System.out.print("Please enter Keywords (comma delimited): ");
                        String keywords_all = stdin.nextLine();
                        String[] keywords = keywords_all.split(",");
                        int serialID = queryEntry.getSerialID(userID);
                        for (int i = 0; i < keywords.length; i++) {
                            boolean keywordAdd = queryEntry.addKeyword(serialID, keywords[i]);
                            if (keywordAdd) {
                                System.out.println(keywords[i] + " successfully added.");
                            }
                        }
                        break;
                    }
                    case 3: {
                        System.out.print("Please enter States (comma delimited): ");
                        String states_all = stdin.nextLine();
                        String[] states = states_all.split(",");
                        int serialID = queryEntry.getSerialID(userID);
                        for (int i = 0; i < states.length; i++) {
                            boolean stateAdd = queryEntry.addState(serialID, states[i]);
                            if (stateAdd) {
                                System.out.println(states[i] + " successfully added.");
                            }
                        }
                        break;
                    }
                    default: {
                        System.out.println("Invalid option entered.");
                    }
                }
            }
            else {
                System.out.println("Invalid username.");
            }
        }


    }
}
