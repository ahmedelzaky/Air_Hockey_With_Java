package com.example.air_hockey_with_java;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.effect.Bloom;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;

public class Player extends Circle {
    private final int id;
    private int xVelocity;
    private int yVelocity;
    private int step;
    private int score = 0;

    private double balldx = 0;
    private double balldy = 0;


    Player(double radius, int id, int Y, int R, int G, int B) {
        super(radius);
        this.id = id;
        this.setCenterX((double) Width / 2);
        this.setCenterY(Y);


        RadialGradient gradientFill = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.rgb(0, 0, 0, 1)),
                new Stop(0.25, Color.rgb(0, 0, 0, 1)),
                new Stop(0.3, Color.rgb(0, 0, 0, 1)),
                new Stop(0.75, Color.rgb(R, G, B, 1)),
                new Stop(1, Color.rgb(R, G, B, .5)));

        Bloom bloom = new Bloom();
        bloom.setThreshold(0.5);
        this.setEffect(bloom);
        this.setFill(gradientFill);


    }


    public int getPlayerId() {
        return id;
    }

    public void keyReleased(KeyEvent event) {


        if (id == 1) {

            if (event.getCode() == KeyCode.I || event.getCode() == KeyCode.K) {
                this.balldy = 0;
            } else if (event.getCode() == KeyCode.J || event.getCode() == KeyCode.L) {
                this.balldx = 0;
            }


        } else if (id == 2) {

            if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.S) {
                this.balldy = 0;
            } else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.D) {
                this.balldx = 0;
            }

        }

    }


    public void keyPressed(KeyEvent event) {
        step = 5;
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
                    this.balldy = -step;
                    break;
                case K:
                    this.balldy = step;
                    break;
                case J:
                    this.balldx = -step;
                    break;
                case L:
                    this.balldx = step;
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
                    this.balldy = -step;
                    break;
                case S:
                    this.balldy = step;
                    break;
                case A:
                    this.balldx = -step;
                    break;
                case D:
                    this.balldx = step;
                    break;
                default:
                    break;
            }


        }

    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void addPoint() {
        ++score;
    }

    public int getScore() {
        return score;
    }


    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }


    public int getxVelocity() {
        return xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }


    public void Move(ActionEvent event) {
        if (id == 1) {
            if (this.getCenterX() + this.balldx < (Width - this.getRadius()) && this.getCenterX() + this.balldx > this.getRadius())
                this.setCenterX(this.getCenterX() + this.balldx);
            if (this.getCenterY() + this.balldy > this.getRadius() && this.getCenterY() + this.balldy < Height / 2 - this.getRadius())
                this.setCenterY(this.getCenterY() + this.balldy);

        } else if (id == 2) {
            if (this.getCenterX() + this.balldx < (Width - this.getRadius()) && this.getCenterX() + this.balldx > this.getRadius())
                this.setCenterX(this.getCenterX() + this.balldx);
            if (this.getCenterY() + this.balldy > (Height / 2 + this.getRadius()) && this.getCenterY() + this.balldy < Height - this.getRadius())
                this.setCenterY(this.getCenterY() + this.balldy);

        }

    }
}







