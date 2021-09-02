package com.example.pong;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
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

    private static double deltaX  = magnitude * Math.cos(angle);
    private static double deltaY = magnitude * Math.sin(angle);

    public void moveP(){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // Check collision with player 1
                if(_rctJoueur1.getBoundsInParent().intersects(_crcPalet.getBoundsInParent())){
                    magnitude *= (magnitude < SPEED_LIMITE) ? ACCELERATION : 1;
                    angle = (Math.PI / 4) * Math.abs((_rctJoueur1.getY() + 75 - _crcPalet.getCenterY() - 12) / 75);
                    deltaX = Math.abs(magnitude * Math.cos(angle));
                    deltaY = (_crcPalet.getCenterY() <= _rctJoueur1.getY() + 75 ? -Math.abs(magnitude * Math.sin(angle)) : Math.abs(magnitude * Math.sin(angle)));
                    // Check collision with player 2
                }else if(_rctJoueur2.getBoundsInParent().intersects(_crcPalet.getBoundsInParent())){
                    magnitude *= (magnitude < SPEED_LIMITE) ? ACCELERATION : 1;
                    deltaX = -deltaX;
                    // Check collision with bottom frame
                }else if(_crcPalet.getCenterY() > BOTTOM_LIMIT){
                    deltaY = -deltaY;
                    // Check collision with top frame
                }else if(_crcPalet.getCenterY() < 0){
                    deltaY = -deltaY;
                }

                // if palet is out of box : Lose and turn inGame bool to false
                if(_crcPalet.getCenterX() < -15){
                    _lblWinLose.setText("Player 1 Lose !");
                    _lblWinLose.setTextFill(Color.RED);
                    // Increase player score
                    MainController.p2Score++;
                    _lblScore.setText("Score : " + MainController.p1Score + " - " + MainController.p2Score);
                    inGame = false;
                    stop();
                }else if(_crcPalet.getCenterX() > 615){
                    _lblWinLose.setText("Player 2 Lose !");
                    _lblWinLose.setTextFill(Color.RED);
                    // Increase player score
                    MainController.p1Score++;
                    _lblScore.setText("Score : " + MainController.p1Score + " - " + MainController.p2Score);
                    inGame = false;
                    stop();
                }

                // Set new position
                _crcPalet.setCenterX(_crcPalet.getCenterX() + deltaX);
                _crcPalet.setCenterY(_crcPalet.getCenterY() + deltaY);
            }
        };
        timer.start();
    }
}
