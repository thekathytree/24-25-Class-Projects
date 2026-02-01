package com.svgs;

import java.io.*;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InspireController {

    @FXML
    private Label authorLbl;

    @FXML
    private Label quoteLbl;

    @FXML
    private  ArrayList<String> quoteList = new ArrayList<>();

    @FXML
    private ArrayList<String> authorList = new ArrayList<>();

    @FXML
    private File quotes;

    @FXML
   public void initialize()
    {
        authorLbl.setText("");
        quoteLbl.setText("");
        quotes = new File("quotes.txt");
        try {
      
            BufferedReader read = new BufferedReader(new FileReader(quotes));
            String line = read.readLine();
            while (line != null) {
              String[] parts = line.split("#");
              quoteList.add(parts[0]);
              authorList.add(parts[1]);
              line = read.readLine();
            }
            read.close();
          }
          catch (Exception e){
            System.out.println(e);
          }
    }

    @FXML
    void inspireMe(ActionEvent event) {
        /* Plan/thought process
         * buffered reader then split at the hash 
         * could have 2 seperate arraylists and have the indices line up
         * ask super cool teacher for help
        */
    
        int num = (int)(Math.random() * quoteList.size());
        quoteLbl.setText(quoteList.get(num));
        authorLbl.setText(authorList.get(num));
    }

}
