package com.example.pong;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.Console;

public class Palet {
    // Members
    private Rectangle _rctJoueur1, _rctJoueur2;
    private Circle _crcPalet;
    private Label _lblCrcPos, _lblWinLose, _lblScore;
    public static boolean inGame = false;

    // Constructor
    Palet(Rectangle rctJoueur1, Rectangle rctJoueur2, Circle crcPalet, Label lblCrcPost, Label lblWinLose, Label lblScore){
        this._rctJoueur1 = rctJoueur1;
        this._rctJoueur2 = rctJoueur2;
        this._crcPalet = crcPalet;
        this._lblCrcPos = lblCrcPost;
        this._lblWinLose = lblWinLose;
        this._lblScore = lblScore;
    }

    // Methods
    // Palet starting pos
    private double x = 300, y = 177;
    private int p1Score = 0, p2Score = 0;
    private String dir = "Left";
    public void moveP(){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // If palet position X is > -15 decrease position
                if(x > -15 && dir == "Left"){
                    x--;
                }else if ( x < 615 && dir == "Right"){
                    x++;
                }else if(x < 0){ // if palet is out of box : Lose and turn inGame bool to false
                    _lblWinLose.setText("Player 1 Lose !");
                    _lblWinLose.setTextFill(Color.RED);
                    // Increase player score
                    p2Score++;
                    _lblScore.setText("Score : " + p1Score + " - " + p2Score);
                    inGame = false;
                    stop();
                }else if(x > 600){
                    _lblWinLose.setText("Player 2 Lose !");
                    _lblWinLose.setTextFill(Color.RED);
                    // Increase player score
                    p1Score++;
                    _lblScore.setText("Score : " + p1Score + " - " + p2Score);
                    inGame = false;
                    stop();
                }

                // Check collision with player 1
                if((y < _rctJoueur1.getY() + _rctJoueur1.getHeight() && y > _rctJoueur1.getY()) && x-25 == _rctJoueur1.getX()){
                    System.out.println("Pong !");
                    dir = "Right";
                }

                // Check collision with player 2
                if((y < _rctJoueur2.getY() + _rctJoueur2.getHeight() && y > _rctJoueur2.getY()) && x+12 == _rctJoueur2.getX()){
                    System.out.println("Pong !");
                    dir = "Left";
                }

                // Set new position
                _crcPalet.setLayoutX(x);
                _crcPalet.setLayoutY(y);
                _lblCrcPos.setText("X: " + _crcPalet.getLayoutX() + " | Y: " + _crcPalet.getLayoutY());
            }
        };
        timer.start();
    }
}
