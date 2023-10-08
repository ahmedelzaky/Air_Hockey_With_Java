package com.example.air_hockey_with_java;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.time.Clock;
import java.util.Random;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;

public class AIPlayer extends Player {
    private Ball ball;
    private Random random = new Random();
    private double xOffset = random.nextDouble(15);
    private double yOffset = random.nextDouble(15);
    private double ayOffset = random.nextDouble(15);
    private double faultOffset = random.nextDouble(1);

    private long time = Clock.systemUTC().millis();
    private Timeline playerAnimation1;
    private Timeline playerAnimation2;
    private double xV = 2;
    private double yV = 2;

    AIPlayer(double radius, int id, double Y, int R, int G, int B, Ball ball) {
        super(radius, id, Y, R, G, B);
        this.ball = ball;

        playerAnimation1 = new Timeline(new KeyFrame(Duration.millis(6), e -> {
            if (this.getCenterY() > ayOffset) this.setCenterY(this.getCenterY() - .1);
        }));
        playerAnimation2 = new Timeline(new KeyFrame(Duration.millis(6), e -> {
            if (this.getCenterY() < ayOffset) this.setCenterY(this.getCenterY() + .1);
        }));
    }


    @Override
    public void Move() {
        if (Clock.systemUTC().millis() - time > 5000) {
            time = Clock.systemUTC().millis();
            faultOffset = random.nextDouble(.5);
            yOffset = random.nextDouble(Height / 2 - 50);
//            System.out.println("faultOffset:  " + faultOffset);
//            System.out.println("CenterY:  " + this.getCenterY());
//            System.out.println("yOffset:  " + yOffset);

            if (this.getCenterY() - yOffset > Height / 2 && ball.getCenterY() < Height / 2) {
                ayOffset = this.getCenterY() - yOffset;
                playerAnimation1.setCycleCount(300);
                playerAnimation1.play();
            } else if (this.getCenterY() + yOffset < Height) {
                ayOffset = this.getCenterY() + yOffset;
                playerAnimation2.setCycleCount(300);
                playerAnimation2.play();
            }
        }
        if (ball.getCenterX() == Width / 2 && ball.getCenterY() > Height / 2) {
            if (this.getCenterY() > Height / 2) {
                this.setCenterY(this.getCenterY() - (yV / 2));
            }
        }
//        if (ball.getCenterY() > this.getCenterY()) {
//            if (ball.getCenterX() < Width / 2) {
//                for (double i = 0.0; i < xV + faultOffset; i += .1)
//                    if (this.getCenterX() - this.getRadius() >= 0) this.setCenterX(this.getCenterX() - .1);
//            } else {
//                for (double i = 0.0; i < xV + faultOffset; i += .1)
//                    if (this.getCenterX() + this.getRadius() <= Width) this.setCenterX(this.getCenterX() + .1);
//            }
//        }

        if (Math.abs(this.getCenterX() - ball.getCenterX()) < xV) {
            if (ball.getCenterX() < Width / 2) {
                this.setCenterX(ball.getCenterX());
            } else {
                this.setCenterX(ball.getCenterX());
            }
        } else {
            if (this.getCenterX() + this.getRadius() > 0 && this.getCenterX() - this.getRadius() < Width && ball.getCenterY() > Height / 2 - 50) {
                if (ball.getCenterX() < Width / 2) {
                    for (double i = 0.0; i < xV + faultOffset; i += .1)
                        if (this.getCenterX() - this.getRadius() >= 0) this.setCenterX(this.getCenterX() - .1);
                } else {
                    for (double i = 0.0; i < xV + faultOffset; i += .1)
                        if (this.getCenterX() + this.getRadius() <= Width) this.setCenterX(this.getCenterX() + .1);
                }
            }
        }
    }
}
