package com.example.pong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class View extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(View.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(View.class.getResource("/CSS/MainCSS.css").toString());
        stage.setTitle("Pong !");
        stage.setResizable(false);

        // Init MoveRectangle object
        MoveRectangle move = new MoveRectangle(scene, fxmlLoader.getController());
        move.move();

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
