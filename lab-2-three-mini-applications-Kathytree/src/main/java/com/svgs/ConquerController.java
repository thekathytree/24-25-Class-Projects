package com.svgs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConquerController {

    @FXML
    private Button againButton;

    @FXML
    private Label conquerLbl;

    @FXML
    private Button paperButton;

    @FXML
    private ImageView playerImg;

    @FXML
    private ImageView pcImg;

    @FXML
    private Button rockButton;

    @FXML
    private Button scissButton;

    @FXML
    private ImageView winImg;

    @FXML
    private Image rock = new Image(getClass().getResource("rock2.png").toString());

    @FXML
    private Image paper = new Image(getClass().getResource("paper2.png").toString());

    @FXML
    private Image scissor = new Image(getClass().getResource("scissors2.png").toString());
    
    @FXML
    private Image sun = new Image(getClass().getResource("sun.png").toString());
    
    @FXML
    private Image moon = new Image(getClass().getResource("moon.png").toString());

    @FXML
    void initialize()
    {
        conquerLbl.setText("");
        rockButton.setVisible(true);
        paperButton.setVisible(true);
        scissButton.setVisible(true);
        againButton.setVisible(false);
    }

    @FXML
    void pcRandom()
    {
        int a = (int) ((Math.random() * 3) + 1);
        if(a == 1){
        pcImg.setImage(rock);}
        if(a == 2){
        pcImg.setImage(paper);}
        if(a == 3){
        pcImg.setImage(scissor);}
    }

    @FXML
    void throwPaper(ActionEvent event) {
        rockButton.setVisible(false);
        paperButton.setVisible(false);
        scissButton.setVisible(false);
        playerImg.setImage(paper);
        win();
    }

    @FXML
    void throwRock(ActionEvent event) {
        rockButton.setVisible(false);
        paperButton.setVisible(false);
        scissButton.setVisible(false);
        playerImg.setImage(rock);
        win();
    }

    @FXML
    void throwScissors(ActionEvent event) {
        rockButton.setVisible(false);
        paperButton.setVisible(false);
        scissButton.setVisible(false);
        playerImg.setImage(scissor);
        win();
    }

    @FXML
    void win()
    {
        pcRandom();
        if(pcImg.getImage().equals(playerImg.getImage())){
            conquerLbl.setText("Neither Conquered");
        }
        else if(pcImg.getImage().equals(rock) && playerImg.getImage().equals(paper))
        {
            conquerLbl.setText("Light conquers!");
            winImg.setImage(sun);
        }
        else if(pcImg.getImage().equals(scissor) && playerImg.getImage().equals(rock))
        {
            conquerLbl.setText("Light conquers!");
            winImg.setImage(sun);

        }
        else if(pcImg.getImage().equals(rock) && playerImg.getImage().equals(scissor))
        {
            conquerLbl.setText("Dark conquers!");
            winImg.setImage(moon);

        }
        else if(pcImg.getImage().equals(paper) && playerImg.getImage().equals(rock))
        {
            conquerLbl.setText("Dark conquers!");
            winImg.setImage(moon);

        }
        else if(pcImg.getImage().equals(scissor) && playerImg.getImage().equals(paper))
        {
            conquerLbl.setText("Dark conquers!");
            winImg.setImage(moon);

        }
        else if(pcImg.getImage().equals(paper) && playerImg.getImage().equals(scissor))
        {
            conquerLbl.setText("Light conquers!");
            winImg.setImage(sun);
        }
        againButton.setVisible(true);
    }

    @FXML
    void playAgain(ActionEvent event) {
        playerImg.setImage(null);
        pcImg.setImage(null);
        winImg.setImage(null);
        initialize();
    }
}