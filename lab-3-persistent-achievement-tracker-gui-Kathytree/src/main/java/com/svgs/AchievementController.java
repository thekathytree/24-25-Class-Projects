package com.svgs;

import java.io.*;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AchievementController {

    @FXML
    private ListView<String> achField;

    @FXML
    private TextField achText;

    @FXML
    private Button earnACButt;

    @FXML
    private ListView<String> playerField;

    @FXML
    private TextField playerText;

    @FXML
    private Button removeACButt;

    @FXML
    private File playerFile = new File("playerFile.txt");

    @FXML
    private HashMap<String, AchievementTracker> playerData = new HashMap<>();

    @FXML
    private ObservableList<String> player;

    @FXML
    private ObservableList<String> achL;

    @FXML
    void initialize()
    {
        earnACButt.setVisible(false);
        removeACButt.setVisible(false);
        achField.setVisible(false);
        achText.setVisible(false);
        player = FXCollections.observableArrayList();
        achL = FXCollections.observableArrayList();
        playerField.setItems(player);
        achField.setItems(achL);
        try {
        BufferedReader read = new BufferedReader(new FileReader(playerFile));
        String line = read.readLine();
        while (line != null) {
            AchievementTracker tr = new AchievementTracker();
            String[] parts = line.split(":");
            String[] ach = parts[1].split(",");
            for(String a : ach)
            {
                tr.earnAchievement(a);
            }
            playerData.put(parts[0], tr);
            line = read.readLine();
        }
        read.close();
        } catch (Exception e) {
        }
        for(String p : playerData.keySet()) {   
            player.add(p);
        }
        playerField.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            unhide();
            loadAchievements();
            System.out.println("Selected user: " + newValue);
        });
    }

    @FXML
    void loadAchievements()
    {
        int i = playerField.getSelectionModel().getSelectedIndex();
        achL.clear();
        AchievementTracker thePlayer = playerData.get(player.get(i));
        for(String a : thePlayer.getAchievements())
        {
            achL.add(a);
        }
    }
    @FXML
    void unhide(){
        earnACButt.setVisible(true);
        removeACButt.setVisible(true);
        achField.setVisible(true);
        achText.setVisible(true);
    }

    @FXML
    void addPlayer(ActionEvent event) {
        AchievementTracker tr = new AchievementTracker();
        if (playerText.getText().isEmpty()) {
            return;
        }
        player.add(playerText.getText());
        playerData.put(playerText.getText(), tr);
        playerText.clear();
    }

    @FXML
    void earnAch(ActionEvent event) {
        int i = playerField.getSelectionModel().getSelectedIndex();
        String ac = achText.getText();
        playerData.get(player.get(i)).earnAchievement(ac);
        loadAchievements();
        achText.clear();
        /* Problem/ thought process
         * achL is the Observable list that holds the achievements.
         * Should there be a new achL for each player? 
         * achL should pull the achievements from the hashmap using the selected player as key
         * Where and how do I intialize achL?
         * Could I possibly have an arrayList of AchievementTrackers 
         * and then add the items to achL to be displayed
         * yet again time to ask super cool teacher for assistance (heres hoping he can see it)
         * I also want to know how to make the the achievement listView and buttons be shown when
         * you click on a name rather than after you add a player.
        */
    }

    @FXML
    void removeAch(ActionEvent event) {
        int i = playerField.getSelectionModel().getSelectedIndex();
        
        int iAch = achField.getSelectionModel().getSelectedIndex();
        String ac = achL.get(iAch);
        if(iAch != -1)
        {
            playerData.get(player.get(i)).removeAchievement(ac);
            achL.remove(iAch);
            loadAchievements();
        }
    }

    @FXML
    void removePlayer(ActionEvent event) {
        int i = playerField.getSelectionModel().getSelectedIndex();
        if(i != -1)
        {
            playerData.remove(player.get(i));
            player.remove(i);
        }
    }

    @FXML
    void savePlay()
    {
        playerFile = new File("playerFile.txt");
        try {
            BufferedWriter write = new BufferedWriter(new FileWriter(playerFile));
            for (String n : playerData.keySet()) {
              write.write(n + ":");
              for(String a : playerData.get(n).getAchievements()) 
              {
                write.write(a + ",");
              }
              write.write("\n");
            }
            write.close();
          } catch (Exception d) {
            System.out.println(d);
          }
    }

}
