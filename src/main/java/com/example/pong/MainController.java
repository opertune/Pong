package com.example.pong;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML
    public Label lblCrcPost;

    private boolean inGame = false;

    // Click button start
    @FXML
    private void clickStartButton(){
        // Check if a game is in progress : false -> launch palet
        if(!MovePalet.inGame){
            MovePalet palet = new MovePalet(rctJoueur1, rctJoueur2, crcPalet, lblCrcPost);
            palet.moveP();
            MovePalet.inGame = true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}