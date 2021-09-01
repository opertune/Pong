package com.example.pong;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class MovePalet {
    // Members
    private Rectangle _rctJoueur1, _rctJoueur2;
    private Circle _crcPalet;
    private Label _lblCrcPost;
    public static boolean inGame = false;

    // Constructor
    MovePalet(Rectangle rctJoueur1, Rectangle rctJoueur2, Circle crcPalet, Label lblCrcPost){
        this._rctJoueur1 = rctJoueur1;
        this._rctJoueur2 = rctJoueur2;
        this._crcPalet = crcPalet;
        this._lblCrcPost = lblCrcPost;
    }

    // Methods
    // Palet starting pos
    private double x = 300, y = 177;
    public void moveP(){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // If palet position X is > -15 decrease position
                if(x > -15){
                    x--;
                }else if(x < 0){ // if palet is out of box : Lose and turn inGame bool to false
                    System.out.println("LOSE !");
                    inGame = false;
                    stop();
                }

                // Check collision with player 1
                if((y < _rctJoueur1.getY() + _rctJoueur1.getHeight() && y > _rctJoueur1.getY()) && x-25 == _rctJoueur1.getX()){
                    System.out.println("Pong !");
                    inGame = false; // PROVISOIR
                    stop();
                }

                // Set new position
                _crcPalet.setLayoutX(x);
                _crcPalet.setLayoutY(y);
                _lblCrcPost.setText("X: " + _crcPalet.getLayoutX() + " | Y: " + _crcPalet.getLayoutY());
            }
        };
        timer.start();
    }
}
