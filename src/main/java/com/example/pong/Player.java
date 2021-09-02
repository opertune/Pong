package com.example.pong;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.EventListener;

public class Player {
    // Members
    private Scene _scene;
    private MainController _controller;

    // Constructor
    Player(Scene scene, MainController controller){
        this._scene = scene;
        this._controller = controller;
    }

    // Methods
    // Rectangles y Pos
    private double p1yOfs = 125, p2yOfs = 125;

    // Players vitesse
    private double vitesse1 = 0, vitesse2 = 0;
    public void move(){
        // On key press (W,S for player 1 and UP, DOWN for player 2) add speed to player rectangle
        _scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case W: vitesse1 = -4; break;
                    case S: vitesse1 = 4; break;
                    case E: vitesse2 = -4; break;
                    case D: vitesse2 = 4; break;
                }
            }
        });

        // On key release remove speed to player rectangle
        _scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case W: vitesse1 = 0; break;
                    case S: vitesse1 = 0; break;
                    case E: vitesse2 = 0; break;
                    case D: vitesse2 = 0; break;
                }
            }
        });

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // add speed to pos player rectangle
                p1yOfs+=vitesse1;
                p2yOfs+=vitesse2;

                // Player 1 box limit
                if(p1yOfs < 0){
                    p1yOfs = 0;
                }else if(p1yOfs > 233){
                    p1yOfs = 233;
                }

                // Player 2 box limit
                if (p2yOfs < 0){
                    p2yOfs = 0;
                }else if(p2yOfs > 233){
                    p2yOfs = 233;
                }

                // Increase or decrease rectangle Y position
                _controller.rctJoueur1.setY(p1yOfs);
                _controller.rctJoueur2.setY(p2yOfs);
            }
        };
        // Launch animation
        animationTimer.start();
    }
}
