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
    public Label lblWinLose;

    @FXML
    public Label lblScore;

    // Click button start
    public static int p1Score = 0, p2Score = 0;
    @FXML
    private void clickStartButton(){
        // Check if a game is in progress : false -> launch palet
        if(!Palet.inGame){
            lblWinLose.setText("");
            crcPalet.setCenterX(300); crcPalet.setCenterY(177);
            Palet palet = new Palet(rctJoueur1, rctJoueur2, crcPalet, lblWinLose, lblScore);
            palet.moveP();
            Palet.inGame = true;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}