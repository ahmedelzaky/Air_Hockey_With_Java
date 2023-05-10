package com.example.air_hockey_with_java;

import javafx.scene.effect.Bloom;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;

public class Player extends Circle {
    private final int id;
    private int xVelocity;
    private int yVelocity;
    private int score = 0;

    private double Xoffset = 0;
    private double Yoffset = 0;

    private double y;
    private double x;

    Player(double radius, int id, int Y, int R, int G, int B) {
        super(radius);
        this.id = id;
        this.x = ((double) Width / 2);

        this.setCenterX((double) Width / 2);
        this.setCenterY(Y);
        this.y = this.getCenterY();


        RadialGradient gradientFill = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.rgb(0, 0, 0, 1)), new Stop(0.25, Color.rgb(0, 0, 0, 1)), new Stop(0.3, Color.rgb(0, 0, 0, 1)), new Stop(0.75, Color.rgb(R, G, B, 1)), new Stop(1, Color.rgb(R, G, B, .5)));

        Bloom bloom = new Bloom();
        bloom.setThreshold(.6);
        this.setEffect(bloom);
        this.setFill(gradientFill);

    }

    public void keyReleased(KeyEvent event) {


        if (id == 1) {

            if (event.getCode() == KeyCode.I || event.getCode() == KeyCode.K) {
                this.Yoffset = 0;
            } else if (event.getCode() == KeyCode.J || event.getCode() == KeyCode.L) {
                this.Xoffset = 0;
            }


        } else if (id == 2) {

            if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.S) {
                this.Yoffset = 0;
            } else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.D) {
                this.Xoffset = 0;
            }

        }

    }


    public void keyPressed(KeyEvent event) {
        int step = 5;
        xVelocity = 5;
        yVelocity = 5;
        if (id == 1) {
            if (event.isShiftDown()) {
                step = 10;
                xVelocity = 10;
                yVelocity = 10;
            }
            switch (event.getCode()) {
                case I:
                    this.Yoffset = -step;


                    break;
                case K:
                    this.Yoffset = step;

                    break;
                case J:
                    this.Xoffset = -step;
                    break;
                case L:
                    this.Xoffset = step;
                    break;
                default:
                    break;
            }

        } else if (id == 2) {

            if (event.isControlDown()) {
                step = 10;
                xVelocity = 10;
                yVelocity = 10;
            }

            switch (event.getCode()) {
                case W:
                    this.Yoffset = -step;
                    break;
                case S:
                    this.Yoffset = step;

                    break;
                case A:
                    this.Xoffset = -step;
                    break;
                case D:
                    this.Xoffset = step;
                    break;
                default:
                    break;
            }


        }

    }


    public void rest() {
        this.setCenterY(y);
        this.setCenterX(x);
        this.score = 0;
    }

    public void addPoint() {
        ++score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }


    public void Move() {
        if (id == 1) {
            if (this.getCenterX() + this.Xoffset < (Width - this.getRadius()) && this.getCenterX() + this.Xoffset > this.getRadius())
                this.setCenterX(this.getCenterX() + this.Xoffset);
            if (this.getCenterY() + this.Yoffset > this.getRadius() && this.getCenterY() + this.Yoffset < (double) Height / 2)
                this.setCenterY(this.getCenterY() + this.Yoffset);

        } else if (id == 2) {
            if (this.getCenterX() + this.Xoffset < (Width - this.getRadius()) && this.getCenterX() + this.Xoffset > this.getRadius())
                this.setCenterX(this.getCenterX() + this.Xoffset);
            if (this.getCenterY() + this.Yoffset > ((double) Height / 2) && this.getCenterY() + this.Yoffset < Height - this.getRadius())
                this.setCenterY(this.getCenterY() + this.Yoffset);

        }

    }
}






