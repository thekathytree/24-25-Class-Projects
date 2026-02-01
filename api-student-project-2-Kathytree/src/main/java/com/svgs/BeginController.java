package com.svgs;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BeginController {
      @FXML
    private Button startButt;

    @FXML
    void startGame(ActionEvent event) throws IOException {
        FXMLLoader floot = new FXMLLoader(getClass().getResource("WondersOfAnimals.fxml"));
        Scene scene;
        scene = new Scene(floot.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        stage = (Stage) startButt.getScene().getWindow();
        stage.close();
        //App.setRoot("WondersOfAnimals");
    }

}
