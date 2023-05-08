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
    Player p1 = new Player(30, 1, 90, 9, 251, 211);
    Player p2 = new Player(30, 2, Height - 90, 235, 248, 117);
    Ball ball = new Ball();
    Text p1Score = new Text();
    Text p2Score = new Text();


    @Override
    public void start(Stage stage) throws FileNotFoundException {

        GameFrame game = new GameFrame();

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

        Button menuBtn = new Button();
        Image menuimg = new Image(new FileInputStream("images\\menu.png"));
        ImageView menuview = new ImageView(menuimg);
        menuBtn.setGraphic(menuview);
        menuBtn.setTranslateX(-19);
        menuBtn.setTranslateY(4);
        menuBtn.setStyle("-fx-background-color: transparent;");

        menuBtn.setOnAction( e->{

        });



        p2Score.setTranslateY(10);

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
        Timeline playersAnimation = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            p1.Move(e);
            p2.Move(e);
        }));
        playersAnimation.setCycleCount(Timeline.INDEFINITE);
        playersAnimation.play();

        Timeline ballAnimation = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            checkCollision();
        }));
        ballAnimation.setCycleCount(Timeline.INDEFINITE);
        ballAnimation.play();

    }

    public void checkCollision() {
        //  System.out.println(p1.getScore() + " : " + p2.getScore());
        if (ball.getCenterX() < Width / 2 + 25 && ball.getCenterX() > Width / 2 - 25 && ball.getCenterY() - ball.getRadius() <= 0) {
            try {
                Thread.sleep(1000); // sleep for 10 milliseconds
            } catch (InterruptedException e) {
                // handle interrupted exception
            }
            p2.addPoint();

            p2Score.setText(String.valueOf(p2.getScore()));
            ball.rest(Height / 2 - 50);
        }
        if (ball.getCenterX() < Width / 2 + 25 && ball.getCenterX() > Width / 2 - 25 && ball.getCenterY() + 15 + ball.getRadius() >= Height) {
            try {
                Thread.sleep(1000); // sleep for 10 milliseconds
            } catch (InterruptedException e) {
                // handle interrupted exception
            }
            p1.addPoint();
            p1Score.setText(String.valueOf(p1.getScore()));
            ball.rest(Height / 2 + 50);
        }

        if (ball.getCenterY() - ball.getRadius() <= 0 || ball.getCenterY() >= Height - 2 * ball.getRadius()) {
            ball.setyVelocity(-ball.getyVelocity());
        }

        if (ball.getCenterX() - ball.getRadius() <= 0 || ball.getCenterX() >= Width - ball.getRadius()) {
            ball.setxVelocity(-ball.getxVelocity());
        }

        if (ball.intersects(p1)) {
            ball.setxVelocity(p1.getxVelocity());
            ball.setyVelocity(p1.getyVelocity());
        }

        if (ball.intersects(p2)) {
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