package com.zmiko.onara;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {


        //Test

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("tasks-view.fxml"));
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
