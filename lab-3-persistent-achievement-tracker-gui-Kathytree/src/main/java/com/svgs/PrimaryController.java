package com.svgs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class PrimaryController {

    @FXML
    private ListView<String> usersListView;

    private ObservableList<String> listOfUsers;

    @FXML
    void initialize() {
        listOfUsers = FXCollections.observableArrayList();
        usersListView.setItems(listOfUsers);

        // add a temporary user to the list
        listOfUsers.add("Anthony");
        
        usersListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // code in here will trigger anytime the selected item changes

            System.out.println("Selected user: " + newValue);
        });
    }

}
