package com.example.air_hockey_with_java;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;

public class Ball extends Circle {

    Ball() {
        /* methods inherited from circle class*/

        //start position
        super.setCenterX((double) Width / 2);
        super.setCenterY((double) Height / 2);
        //radius of the ball
        super.setRadius(15);
        super.setRadius(15);


        super.setFill(Color.rgb(237, 228, 217));
    }
}