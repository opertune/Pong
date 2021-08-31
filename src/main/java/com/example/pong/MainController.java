package com.example.pong;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public Rectangle rctJoueur1;

    @FXML
    public Rectangle rctJoueur2;

    @FXML
    public Circle crcPalet;

    @FXML
    public Button btnStart;

    // Click button start
    @FXML
    private void clickStartButton(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}