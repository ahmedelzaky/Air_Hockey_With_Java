package com.example.air_hockey_with_java;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;

public class Ball extends Circle {

    private int xVelocity;
    private int yVelocity;
    int random;
    Ball() {
        this.setCenterX((double) Width / 2);
        this.setCenterY((double) Height / 2);
        this.setRadius(15);
        this.setFill(Color.rgb(237, 228, 217));

    }
}
