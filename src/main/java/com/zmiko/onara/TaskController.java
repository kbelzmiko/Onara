package com.zmiko.onara;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TaskController {
    private TaskLogic tasks = new TaskLogic();

    @FXML
    public Button modeButton;
    @FXML
    private Button completeButton;
    @FXML
    private TextField newTask;
    @FXML
    public VBox tasksVb;

    public void initialize() {
        populateVbox();
    }

    @FXML
    public void newTask() {
        this.tasks.push(this.newTask.getText());
        populateVbox();
        this.newTask.setText("");
    }

    @FXML
    public void modeChange() {
        if (this.tasks.getMode() == mode.Queue) {
            this.tasks.setMode(mode.Stack);
            this.modeButton.setText("Stack");
        } else {
            this.tasks.setMode(mode.Queue);
            this.modeButton.setText("Queue");
        }
        populateVbox();
    }

    @FXML
    public void complete() {
        this.tasks.pop();
        populateVbox();
    }

    @FXML
    public void populateVbox() {
        this.tasksVb.getChildren().clear();
        ArrayList<String> tasksBox = this.tasks.getTasks();

        if (this.tasks.getMode() == mode.Stack) {
            ArrayList<String> stackBox = new ArrayList<>();
            for (String s : tasksBox) {
                stackBox.add(0, s);
            }
            for (String s : stackBox) {
                Label label = new Label(s);
                this.tasksVb.getChildren().add(label);
            }
        } else {
            for (String s : tasksBox) {
                Label label = new Label(s);
                this.tasksVb.getChildren().add(label);
            }
        }


    }
}
