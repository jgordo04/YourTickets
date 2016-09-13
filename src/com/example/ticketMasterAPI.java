package com.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketmaster.api.discovery.DiscoveryApi;
import java.io.*;
import java.util.*;
import java.net.*;
import javax.swing.*;



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
        //JSONObject sb = new JSONObject();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        conn.disconnect();

        ObjectMapper mapper = new ObjectMapper();

        EventResponse results = mapper.readValue(sb.toString(),EventResponse.class);

        System.out.println(results);
        return results;

    }


    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);

        System.out.print("Keywords (comma delimited): ");
        String keywords_all = stdin.nextLine();
        String [] keywords = keywords_all.split(",");
        //List<String> keyword = new List<String>; //{"Hutchinson"};//,"Degraw","Grammer"};

        System.out.print("State: ");
        String state = stdin.nextLine();
        String apikey = "uQ2nE4Pd9KYfdZSKsh6Knv5YCqaSJe0T";
        DiscoveryApi api = new DiscoveryApi(apikey);
        String dateString = "";
        String venueString = "";
        String nameString = "";

        for ( int j = 0; j < keywords.length; j++ ) {
            String uri =
                    "https://app.ticketmaster.com/discovery/v2/events.json?keyword=" + keywords[j] +
                            "&stateCode=" + state +
                            "&countryCode=US&apikey=uQ2nE4Pd9KYfdZSKsh6Knv5YCqaSJe0T";

            EventResponse result = new EventResponse();
            try {
                result = httpGet(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(result);
            nameString = result.getEmbedded().getEvents().get(0).getName();
            dateString = result.getEmbedded().getEvents().get(0).getDate().getStartDate().getDate();
            venueString = result.getEmbedded().getEvents().get(0).getSecondEmbedded().getVenue().get(0).getName();  //getZero().getName();

            System.out.println("----- " + keywords[j] + "-----");
            System.out.println(nameString + ": " + venueString + " - " + dateString);

        }

        String [] resultsArray = {nameString,venueString,dateString};
        JFrame guiFrame = new JFrame();  //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Results"); guiFrame.setSize(300,250);  //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        final JPanel comboPanel = new JPanel();
        JLabel comboLbl = new JLabel(keywords[0] + ":" );

        comboPanel.add(comboLbl);
        for ( int i = 0; i < resultsArray.length; i++ ){
            JLabel result = new JLabel(resultsArray[i]);
            comboPanel.add(result);
        }
        comboPanel.setVisible(true);
        guiFrame.add(comboPanel);
        guiFrame.setVisible(true);

    }
}
