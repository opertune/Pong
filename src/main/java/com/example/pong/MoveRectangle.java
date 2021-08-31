package com.example.pong;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class MoveRectangle {
    // Members
    Scene _scene;
    MainController _controller;

    // Constructor
    MoveRectangle(Scene scene, MainController controller){
        this._scene = scene;
        this._controller = controller;
    }

    // Methods
    private double p1yOfs = 0, p2yOfs = 0;
    public void move(){
        // On key press (W,S for player 1 and UP, DOWN for player 2) increase or decrease position
        _scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case W: p1yOfs += -9; break;
                    case S: p1yOfs += 9; break;
                    case E: p2yOfs += -9; break;
                    case D: p2yOfs += 9; break;
                }
            }
        });

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                // Player 1 box limit
                if(p1yOfs < -123){
                    p1yOfs = -123;
                }else if(p1yOfs > 108){
                    p1yOfs = 108;
                }

                // Player 2 box limit
                if (p2yOfs < -123){
                    p2yOfs = -123;
                }else if(p2yOfs > 108){
                    p2yOfs = 108;
                }

                // Increase or decrease rectangle Y position
                _controller.rctJoueur1.setY(p1yOfs);
                _controller.rctJoueur2.setY(p2yOfs);
            }
        };
        animationTimer.start();
    }
}
