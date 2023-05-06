package com.example.air_hockey_with_java;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Game extends Application {
    final static int Height = 700;
    final static int Width = (int) (Height * 0.5);
    Player p1 = new Player(30, 1, 90, 0, 255, 255);
    Player p2 = new Player(30, 2, Height - 90, 102, 255, 51);
    Ball ball = new Ball();
    private Timeline ballAnimation;


    @Override
    public void start(Stage stage) throws FileNotFoundException {

        GameFrame game = new GameFrame();

        game.getChildren().add(p1);
        game.getChildren().add(p2);
        game.getChildren().add(ball);

        Scene scene = new Scene(game, Width, Height);

        // Add the icon to the list of icons for the stage
        Image icon = new Image(new FileInputStream("image\\icon.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Air Hockey Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


   /*     scene.setOnMouseMoved(e -> {
            p1.setCenterY(e.getY());
            p1.setCenterX(e.getX());
        });*/


        scene.setOnKeyPressed(e -> {
            p1.keyPressed(e);
            p2.keyPressed(e);
        });

        ballAnimation = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            checkCollision();
        }));
        ballAnimation.setCycleCount(Timeline.INDEFINITE);
        ballAnimation.play();

    }

    public void checkCollision() {
        System.out.println(p1.getScore() + " : " + p2.getScore());
        //bounce ball off top & bottom window edges
        if (ball.getCenterX() < Width / 2 + 25 && ball.getCenterX() > Width / 2 - 25 && ball.getCenterY() - ball.getRadius() <= 0) {
            try {
                Thread.sleep(1000); // sleep for 10 milliseconds
            } catch (InterruptedException e) {
                // handle interrupted exception
            }
            p2.addPoint();
            ball.rest(Height / 2 - 50);
        }
        if (ball.getCenterX() < Width / 2 + 25 && ball.getCenterX() > Width / 2 - 25 && ball.getCenterY() + 2 * ball.getRadius() >= Height) {
            try {
                Thread.sleep(1000); // sleep for 10 milliseconds
            } catch (InterruptedException e) {
                // handle interrupted exception
            }
            p1.addPoint();
            ball.rest(Height / 2 + 50);
        }

        if (ball.getCenterY() - ball.getRadius() <= 0 || ball.getCenterY() >= Height - 2 * ball.getRadius()) {
            ball.setyVelocity(-ball.getyVelocity());

        }
        if (ball.getCenterX() - ball.getRadius() <= 0 || ball.getCenterX() >= Width - ball.getRadius()) {

            ball.setxVelocity(-ball.getxVelocity());
        }


        //bounce ball off paddles
        if (ball.intersects(p1)) {
            double ballCenterX = ball.getCenterX();
            double paddleCenterX = p1.getCenterX();
            double ballVelocityX = ball.getxVelocity();
            double ballVelocityY = ball.getyVelocity();
            double newVelocityX = (ballCenterX - paddleCenterX) / 5;
            double newVelocityY = Math.sqrt(25 - Math.pow(newVelocityX, 2));
            ball.setxVelocity(p1.getxVelocity());
            ball.setyVelocity(p1.getyVelocity());
        }
        if (ball.intersects(p2)) {
            double ballCenterX = ball.getCenterX();
            double paddleCenterX = p2.getCenterX();
            double ballVelocityX = ball.getxVelocity();
            double ballVelocityY = ball.getyVelocity();
            double newVelocityX = (ballCenterX - paddleCenterX) / 5;
            double newVelocityY = -Math.sqrt(25 - Math.pow(newVelocityX, 2));
            ball.setxVelocity(p2.getxVelocity());
            ball.setyVelocity(-p2.getyVelocity());
        }

        // move the ball
        ball.move();
    }


    public static void main(String[] args) {
        launch();
    }

}