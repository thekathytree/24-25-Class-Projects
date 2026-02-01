package com.svgs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TaskController {

    @FXML
    private TextField enterTask;

    @FXML
    private ListView<Text> taskList;

    private ObservableList<Text> tasks;

    @FXML
    void initialize() {
        tasks = FXCollections.observableArrayList();
        taskList.setItems(tasks);

    }

    @FXML
    void addTask(ActionEvent event) {
        if(enterTask.getText().isEmpty())
        {
            return;
        }
        tasks.add(new Text(enterTask.getText()));
        enterTask.clear();
    }

    @FXML
    void completeTask(ActionEvent event) {
        int i = taskList.getSelectionModel().getSelectedIndex();
        if(i != -1)
        {
            tasks.get(i).setStrikethrough(true);            
        }
    }

    @FXML
    void removeTask(ActionEvent event) {
        int i = taskList.getSelectionModel().getSelectedIndex();
        if(i != -1)
        {
            tasks.remove(i);
        }
    }

}