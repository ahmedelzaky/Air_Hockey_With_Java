package com.example.air_hockey_with_java;

import javafx.scene.effect.Bloom;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

import static com.example.air_hockey_with_java.Game.Width;

public class player extends Circle {
    private int id;
    private int step = 5;

    player(int id, int Y, int R, int G, int B) {
        this.id = id;
        this.setCenterX((double) Width / 2);
        this.setCenterY(Y);
        this.setRadius(30);
        RadialGradient gradientFill = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.rgb(0, 0, 0, 1)), new Stop(0.25, Color.rgb(0, 0, 0, 1)), new Stop(0.3, Color.rgb(0, 0, 0, 1)), new Stop(0.75, Color.rgb(R, G, B, 1)), new Stop(1, Color.rgb(R, G, B, .8)));
        // Set the fill and stroke of the player circle
        Bloom bloom = new Bloom();
        bloom.setThreshold(0.5);
        this.setEffect(bloom);
        this.setFill(gradientFill);
    }

 /*   public void keyPreesd(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getCode() == KeyCode.UP ) {
                    this.setCenterY(-step);
                }
        }

    }*/


}
