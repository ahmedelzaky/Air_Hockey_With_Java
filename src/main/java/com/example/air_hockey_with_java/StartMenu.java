package com.example.air_hockey_with_java;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;


public class StartMenu extends Pane {
    private Font fnt = Font.font("Time New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
    private Button start;

    StartMenu() {
        GameFrame mGame = new GameFrame();
        Ball mBall = new Ball();
        mBall.setYVelocity(Math.random() * 10);
        mBall.setXVelocity(Math.random() * 10);
        mGame.getChildren().add(mBall);
        mGame.setEffect(new GaussianBlur());
        this.getChildren().add(mGame);


        start = new Button("Start");
        start.setPrefSize(150, 25);
        start.setTextFill(Color.WHITE);
        start.setStyle("-fx-background-color:  transparent;" + "-fx-border-color:  rgb(200, 34, 255 , .6);" + "-fx-border-width:5;" + "-fx-border-radius:50;" + "-fx-cursor: pointer;");
        start.setFont(fnt);
        start.setLayoutX(Width / 2 - 75);
        start.setLayoutY(Height / 2 - 25);
        double recWidth = Width - 100;
        double recHeight = Height - 550;
        //Create a Rectangle to add button on it
        Rectangle rectangle = new Rectangle(recWidth, recHeight);
        rectangle.setLayoutX((double) Width / 2 - recWidth / 2);
        rectangle.setLayoutY((double) Height / 2 - recHeight / 2);
        rectangle.setFill(Color.rgb(200, 34, 255, .2));
        rectangle.setStroke(Color.rgb(200, 34, 255, .8));
        rectangle.setStrokeWidth(5);

        this.getChildren().addAll(start, rectangle);

        start.setOnAction(e -> {
        });

    }


    public void getStart(Stage stage, Scene scene) {
        stage.setScene(scene);
    }
}
