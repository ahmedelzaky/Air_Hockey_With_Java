package com.example.air_hockey_with_java;

import javafx.scene.effect.Bloom;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;

public class player extends Circle {
    private final int id;
    private int step;
    private double x;
    private double y;
    private double radius;

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

    public void keyPreesd(KeyEvent event) {
        x = this.getCenterX();
        y = this.getCenterY();
        radius = this.getRadius();
        System.out.println(this.id);
        step = 5;


        if (id == 1) {
            if (event.isShiftDown()) {
                step = 10;
            }
            switch (event.getCode()) {
                case UP:
                    if (y - step >= radius) {
                        y -= step;
                    }
                    break;
                case DOWN:
                    if (y + step <= (double) Height / 2 - radius) {
                        y += step;
                    }
                    break;
                case LEFT:
                    if (x - step >= radius) {
                        x -= step;
                    }
                    break;
                case RIGHT:
                    if (x + step <= Width - radius) {
                        x += step;
                    }
                    break;
                default:
                    break;

            }
            this.setCenterY(y);
            this.setCenterX(x);
        } else if (id == 2) {
            if (event.isControlDown()) {
                step = 10;
            }
            switch (event.getCode()) {
                case W:
                    if (y - step >= (double) Height / 2 + radius) {
                        y -= step;
                    }
                    break;
                case S:
                    if (y + step <= Height - radius) {
                        y += step;
                    }
                    break;
                case A:
                    if (x - step >= radius) {
                        x -= step;
                    }
                    break;
                case D:
                    if (x + step <= Width - radius) {
                        x += step;
                    }
                    break;
                default:
                    break;
            }
            this.setCenterY(y);
            this.setCenterX(x);
        }

    }
}





