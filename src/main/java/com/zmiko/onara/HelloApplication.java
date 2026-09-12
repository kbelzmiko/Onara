package com.zmiko.onara;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 280, 480);
        Scene 
        stage.setMinWidth(280);
        stage.setMaxWidth(280);
        stage.setMinHeight(480);
        stage.setMaxHeight(480);
        stage.setTitle("Onara");
        stage.setScene(scene);
        stage.show();
    }
}
