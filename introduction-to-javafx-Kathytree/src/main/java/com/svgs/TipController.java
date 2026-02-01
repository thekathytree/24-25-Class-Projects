package com.svgs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class TipController {

    @FXML
    private TextField amtBox;

    @FXML
    private Button calcButton;

    @FXML
    private Label prcntLbl;

    @FXML
    private Slider prcntSlider;

    @FXML
    private TextField tipBox;

    @FXML
    private TextField totalBox;

    @FXML
    public void initialize(){
        prcntSlider.setValue(15);
    }

    @FXML
    void calculate(ActionEvent event) {
        try {
        String amtText = amtBox.getText();
        double amt = Double.parseDouble(amtText);
        double prcnt = prcntSlider.getValue()/100;
        double tipAmt = amt*prcnt;
        tipBox.setText(String.format("%.2f", tipAmt));

        double grndTotal = amt + tipAmt;
        totalBox.setText(String.format("%.2f", grndTotal));
    }catch (Exception e){
        amtBox.clear();
    }
    }

    @FXML
    void sliderDone(MouseEvent event) {
        prcntLbl.setText(String.format("%.0f",prcntSlider.getValue()) + "%");
       // System.out.println("Correctly");
    }

}
