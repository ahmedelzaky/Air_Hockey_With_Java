package com.example.air_hockey_with_java;

import javafx.animation.FadeTransition;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;

public class Ball extends Circle {

    private double xVelocity = 0;
    private double yVelocity = 0;


    Ball() {
        this.setCenterX((double) Width / 2);
        this.setCenterY((double) Height / 2);
        this.setRadius(15);
        RadialGradient gradientFill = new RadialGradient(
                0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(252, 243, 64, .5)),
                new Stop(0.25, Color.rgb(252, 243, 64)),
                new Stop(0.5, Color.rgb(252, 243, 64)),
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
        double newCenterX ;
        double newCenterY ;
        if(getXVelocity()>=0){
            for(double i=0; i<getXVelocity(); i+=.5){
                if (getCenterX() <= Width - getRadius()) {
                newCenterX = getCenterX() + .5;
                setCenterX(newCenterX);
           }}
        }else{
            for(double i=getXVelocity(); i<0; i+=.5) {
                if (getCenterX() - getRadius() >= 0 ) {
                newCenterX = getCenterX() - .5;
                setCenterX(newCenterX);
            }}
        }
        if(getYVelocity()>=0){
            for(double i=0; i<getYVelocity(); i+=.5){
                if ( getCenterY() <= Height - getRadius()){
                newCenterY = getCenterY() + .5;
                setCenterY(newCenterY);
            }}
        }else{
            for(double i=getYVelocity(); i<0; i+=.5){
                if (getCenterY() - getRadius() >= 0 ){
                newCenterY = getCenterY() - .5;
                setCenterY(newCenterY);
            }}
        }

    }

    public void rest(int y) {

        this.setCenterX((double) Width / 2);
        this.setCenterY(y);
        this.xVelocity = 0;
        this.yVelocity = 0;
        FadeTransition ballFad = new FadeTransition(Duration.millis(400), this);
        ballFad.setFromValue(.3);
        ballFad.setToValue(1);
        ballFad.setCycleCount(3);
        ballFad.play();
    }


    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }


    public double getXVelocity() {
        return xVelocity;
    }

    public double getYVelocity() {
        return yVelocity;
    }

}
