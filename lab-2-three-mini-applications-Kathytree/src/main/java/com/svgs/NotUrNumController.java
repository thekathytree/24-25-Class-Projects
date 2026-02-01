package com.svgs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NotUrNumController {

    @FXML
    private Button againButton;

    @FXML
    private TextField gssBox;

    @FXML
    private Button gssButton;

    @FXML
    private Label winLbl;


    @FXML
    private int lemonAmt;

    @FXML 
    public void initialize() {
    winLbl.setText("");
    againButton.setVisible(false);
    lemonAmt = (int) (Math.random() * 100) + 1;
    gssBox.clear();
}


    @FXML
    void guess(ActionEvent event) {
        try {
            
            int gss = Integer.parseInt(gssBox.getText());
            
            if(gss < lemonAmt)
            {
                winLbl.setText("Higher");
            }
            else if (gss > lemonAmt)
            {
                winLbl.setText("Lower");
            }
            
            if (gss == lemonAmt)
            {
                winLbl.setText("You won!");
                againButton.setVisible(true);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    void playAgain(ActionEvent event) {
        initialize();
        guess(event);
    }

}
