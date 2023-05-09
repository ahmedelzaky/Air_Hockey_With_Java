package com.example.air_hockey_with_java;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    Text p1Score = new Text();
    Text p2Score = new Text();
    private Timeline ballAnimation;
    GameFrame game = new GameFrame();


    @Override
    public void start(Stage stage) throws FileNotFoundException {


        game.getChildren().add(p1);
        game.getChildren().add(p2);
        game.getChildren().add(ball);

        p1Score.setText(String.valueOf(p1.getScore()));
        p2Score.setText(String.valueOf(p2.getScore()));
        p1Score.setFill(Color.WHITE);
        p2Score.setFill(Color.WHITE);
        p1Score.setFont(Font.font("Arial", 36));
        p2Score.setFont(Font.font("Arial", 36));
        VBox conter = new VBox(10);
        conter.setPrefWidth(150);
        conter.setLayoutX(Width - 30);
        conter.setLayoutY(Height / 2 - 75);
        // menu Button
        Button menuBtn = new Button();
        Image menuimg = new Image(new FileInputStream("images\\menu.png"));
        ImageView menueview = new ImageView(menuimg);
        menueview.setFitHeight(30);
        menueview.setFitWidth(30);


        menuBtn.setGraphic(menueview);
        menuBtn.setLayoutY(Height / 2);
        menuBtn.setTranslateX(-15);
        menuBtn.setTranslateY(5);

        menuBtn.setStyle("-fx-background-color: transparent;");
        p1Score.setTranslateY(5);
        conter.getChildren().addAll(p1Score, menuBtn, p2Score);


        game.getChildren().add(conter);


        Scene scene = new Scene(game, Width, Height);

        // Add the icon to the list of icons for the stage
        Image icon = new Image(new FileInputStream("images\\icon.png"));
        stage.getIcons().add(icon);

/*
        Media media = new Media("file:///path/to/audio/file.mp3");

        // Create a MediaPlayer with the Media instance
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Play the audio
        mediaPlayer.play();
*/


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
        scene.setOnKeyReleased(e -> {
            p1.keyReleased(e);
            p2.keyReleased(e);
        });

        Timeline panimation = new Timeline(new KeyFrame(Duration.millis(16), e -> {

            p1.Move();
            p2.Move();

        })
        );
        panimation.setCycleCount(Timeline.INDEFINITE);
        panimation.play();

        ballAnimation = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            checkCollision();
        }));
        ballAnimation.setCycleCount(Timeline.INDEFINITE);
        ballAnimation.play();

    }

    public void checkCollision() {
        //  System.out.println(p1.getScore() + " : " + p2.getScore());
        if (ball.getCenterX() < Width / 2 + game.getArcRaduis() && ball.getCenterX() > Width / 2 - game.getArcRaduis() && ball.getCenterY() - ball.getRadius() <= 0) {
            try {
                Thread.sleep(1000); // sleep for 10 milliseconds
            } catch (InterruptedException e) {
                // handle interrupted exception
            }
            p2.addPoint();
            p2Score.setText(String.valueOf(p2.getScore()));
            ball.rest(Height / 2 - 50);
        }
        if (ball.getCenterX() < Width / 2 + game.getArcRaduis() && ball.getCenterX() > Width / 2 - game.getArcRaduis() && ball.getCenterY() + ball.getRadius() >= Height) {
            try {
                Thread.sleep(1000); // sleep for 10 milliseconds
            } catch (InterruptedException e) {
                // handle interrupted exception
            }
            p1.addPoint();
            p1Score.setText(String.valueOf(p1.getScore()));
            ball.rest(Height / 2 + 50);
        }

        if (ball.getCenterY() - ball.getRadius() <= 0 || ball.getCenterY() >= Height - ball.getRadius()) {
            ball.setyVelocity(-ball.getyVelocity());
        }

        if (ball.getCenterX() - ball.getRadius() + 5 <= 0 || ball.getCenterX() >= Width - ball.getRadius() - 5) {
            ball.setxVelocity(-ball.getxVelocity());
        }

        if (ball.intersects(p1)) {

            if (ball.getCenterX() - p1.getCenterX() == 0) {
                if (ball.getCenterY() < p1.getCenterY()) {
                    ball.setxVelocity(0);
                    ball.setyVelocity(-p1.getyVelocity());
                } else {
                    ball.setxVelocity(0);
                    ball.setyVelocity(p1.getyVelocity());
                }
            } else if (ball.getCenterX() < p1.getCenterX()) {
                if (ball.getCenterY() < p1.getCenterY()) {
                    ball.setxVelocity(-p1.getxVelocity());
                    ball.setyVelocity(-p1.getyVelocity());
                } else {
                    ball.setxVelocity(-p1.getxVelocity());
                    ball.setyVelocity(p1.getyVelocity());
                }


            } else {
                if (ball.getCenterY() < p1.getCenterY()) {
                    ball.setxVelocity(p1.getxVelocity());
                    ball.setyVelocity(-p1.getyVelocity());
                } else {
                    ball.setxVelocity(p1.getxVelocity());
                    ball.setyVelocity(p1.getyVelocity());
                }

            }

        }

        if (ball.intersects(p2)) {

            if (ball.getCenterX() - p2.getCenterX() == 0) {
                if (ball.getCenterY() > p2.getCenterY()) {
                    ball.setxVelocity(0);
                    ball.setyVelocity(p2.getyVelocity());
                } else {
                    ball.setxVelocity(0);
                    ball.setyVelocity(-p2.getyVelocity());
                }
            } else if (ball.getCenterX() < p2.getCenterX()) {
                if (ball.getCenterY() > p2.getCenterY()) {
                    ball.setxVelocity(-p2.getxVelocity());
                    ball.setyVelocity(p2.getyVelocity());
                } else {
                    ball.setxVelocity(-p2.getxVelocity());
                    ball.setyVelocity(-p2.getyVelocity());
                }

            } else {
                if (ball.getCenterY() > p2.getCenterY()) {
                    ball.setxVelocity(p2.getxVelocity());
                    ball.setyVelocity(p2.getyVelocity());
                } else {
                    ball.setxVelocity(p2.getxVelocity());
                    ball.setyVelocity(-p2.getyVelocity());
                }
            }
        }
        // move the ball
        ball.move();
    }


    public static void main(String[] args) {
        launch();
    }

}