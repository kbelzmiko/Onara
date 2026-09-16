package com.zmiko.onara;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

enum mode {
    Queue,
    Stack
}

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        TaskLogic tasks;

        tasks = new TaskLogic();

        //Test
        tasks.push("tets");
        tasks.push("tets2");
        tasks.push("tets3");


        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 280, 480);
        stage.setMinWidth(280);
        stage.setMaxWidth(280);
        stage.setMinHeight(480);
        stage.setMaxHeight(480);
        stage.setTitle("Onara");
        stage.setScene(scene);
        stage.show();
    }
}
