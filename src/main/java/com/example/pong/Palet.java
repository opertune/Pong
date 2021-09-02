package com.example.pong;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Palet {
    // Members
    private Rectangle _rctJoueur1, _rctJoueur2;
    private Circle _crcPalet;
    private Label _lblWinLose, _lblScore;
    public static boolean inGame = false;

    // Constructor
    Palet(Rectangle rctJoueur1, Rectangle rctJoueur2, Circle crcPalet, Label lblWinLose, Label lblScore){
        this._rctJoueur1 = rctJoueur1;
        this._rctJoueur2 = rctJoueur2;
        this._crcPalet = crcPalet;
        this._lblWinLose = lblWinLose;
        this._lblScore = lblScore;
    }

    // Methods
    // Palet starting pos
    private static final double BOTTOM_LIMIT = 335;
    private static final double INITIAL_VX = 0;
    private static final double INITIAL_VY = 0;
    private static final double SPEED_LIMITE = 30;
    private static final double ACCELERATION = 1.1;

    private static double angle = Math.atan2(INITIAL_VY, INITIAL_VX);
    private static double magnitude = Math.sqrt(3);

    private static double deltaX = magnitude * Math.cos(angle);
    private static double deltaY = magnitude * Math.sin(angle);

    public void moveP(){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // Check collision with player 1
                if(_rctJoueur1.getBoundsInParent().intersects(_crcPalet.getBoundsInParent())){
                    collisionPlayer(true);

                    // Check collision with player 2
                }else if(_rctJoueur2.getBoundsInParent().intersects(_crcPalet.getBoundsInParent())){
                    collisionPlayer(false);

                    // Check collision with bottom frame
                }else if(_crcPalet.getCenterY() > BOTTOM_LIMIT){
                    deltaY = -deltaY;

                    // Check collision with top frame
                }else if(_crcPalet.getCenterY() < 0){
                    deltaY = -deltaY;
                }

                // if palet is out of box Left side
                if(_crcPalet.getCenterX() < -15){
                    outOfGrid("Player 1 Lose !", true);
                    // Stop AnimationTimer loop
                    stop();

                    // if palet is out of box Right side
                }else if(_crcPalet.getCenterX() > 615){
                    outOfGrid("Player 2 Lose !", false);
                    // Stop AnimationTimer loop
                    stop();
                }

                // Set new position
                _crcPalet.setCenterX(_crcPalet.getCenterX() + deltaX);
                _crcPalet.setCenterY(_crcPalet.getCenterY() + deltaY);
            }
        };
        timer.start();
    }

    // Collision between player and palet
    void collisionPlayer(boolean player1){
        // increased palet speed
        magnitude *= (magnitude < SPEED_LIMITE) ? ACCELERATION : 1;

        // more the palet are close to the center less the angle are wide
        angle = (Math.PI / 4) * Math.abs((_rctJoueur1.getY() + 75 - _crcPalet.getCenterY() - 12) / 75);
        if (player1){
            deltaX = Math.abs(magnitude * Math.cos(angle));
        }else{
            deltaX = -Math.abs(magnitude * Math.cos(angle));
        }

        // value depends on the place or hits the palet : high or low half
        deltaY = (_crcPalet.getCenterY() <= _rctJoueur1.getY() + 75 ? -Math.abs(magnitude * Math.sin(angle)) : Math.abs(magnitude * Math.sin(angle)));
    }

    // Palet out of the grid (left and right side)
    void outOfGrid(String txt, boolean player1){
        _lblWinLose.setText(txt);
        _lblWinLose.setTextFill(Color.RED);

        // Increase player score
        if(player1){
            MainController.p2Score++;
        }else{
            MainController.p1Score++;
        }
        _lblScore.setText("Score : " + MainController.p1Score + " - " + MainController.p2Score);
        inGame = false;

        // Reset magnitude and delta
        magnitude = Math.sqrt(3);
        deltaX = magnitude * Math.cos(Math.atan2(0, 0));
        deltaY = magnitude * Math.sin(Math.atan2(0, 0));
    }
}
