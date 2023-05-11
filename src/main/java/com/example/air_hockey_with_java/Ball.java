package com.example.air_hockey_with_java;

import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;
import static javafx.scene.paint.Color.rgb;

public class Ball extends Circle {

    private int xVelocity = 0;
    private int yVelocity = 0;


    Ball() {
        this.setCenterX((double) Width / 2);
        this.setCenterY((double) Height / 2);
        this.setRadius(15);
        RadialGradient gradientFill = new RadialGradient(
                0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(252, 243, 64 , .5)),
                new Stop(0.25, Color.rgb(252, 243, 64)),
                new Stop(0.3, Color.rgb(252, 243, 64)),
                new Stop(0.75, Color.rgb(252, 243, 64)),
                new Stop(1, Color.rgb(252, 243, 64, .5)));

        Bloom bloom = new Bloom();
        bloom.setThreshold(.6);
        this.setEffect(bloom);
        this.setFill(gradientFill);

    }


    public boolean intersects(Player player) {
        double ballCenterX = getCenterX();
        double ballCenterY = getCenterY();
        double playerCenterX = player.getCenterX();
        double playerCenterY = player.getCenterY();
        double distance = Math.sqrt(Math.pow(ballCenterX - playerCenterX, 2) + Math.pow(ballCenterY - playerCenterY, 2));
        return distance <= getRadius() + player.getRadius();
    }

    public void move() {
        double newCenterX = getCenterX() + getxVelocity();
        double newCenterY = getCenterY() + getyVelocity();
        setCenterX(newCenterX);
        setCenterY(newCenterY);
    }

    public void rest(int y) {

        this.setCenterX((double) Width / 2);
        this.setCenterY(y);
        this.xVelocity = 0;
        this.yVelocity = 0;
    }



    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
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

}
