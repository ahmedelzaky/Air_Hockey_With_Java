package com.example.air_hockey_with_java;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;


public class StartMenu extends Pane {
    private final Ball mBall;
    private boolean play = false;
    private boolean addMenu = false;
    private boolean move = false;


    StartMenu(Menu menu) {
        GameFrame mGame = new GameFrame();
        mBall = new Ball();
        mBall.setYVelocity(Math.random() * 10);
        mBall.setXVelocity(Math.random() * 10);
        mGame.getChildren().add(mBall);
        mGame.setEffect(new GaussianBlur());
        this.getChildren().add(mGame);


        Button start = new Button("Start");
        start.setPrefSize(150, 25);
        start.setTextFill(Color.WHITE);
        start.setStyle("-fx-background-color:  transparent;" + "-fx-border-color:  rgb(200, 34, 255 , .6);" + "-fx-border-width:5;" + "-fx-border-radius:50;" + "-fx-cursor: pointer;");
        Font fnt = Font.font("Time New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);
        start.setFont(fnt);
        start.setLayoutX((double) Width / 2 - 75);
        start.setLayoutY((double) Height / 2 - 25);
        double recWidth = Width - 100;
        double recHeight = Height - 550;

        //Create a Rectangle to add button on it
        Rectangle rectangle = new Rectangle(recWidth, recHeight);
        rectangle.setLayoutX((double) Width / 2 - recWidth / 2);
        rectangle.setLayoutY((double) Height / 2 - recHeight / 2);
        rectangle.setFill(Color.rgb(200, 34, 255, .2));
        rectangle.setStroke(Color.rgb(200, 34, 255, .8));
        rectangle.setStrokeWidth(5);

        this.getChildren().addAll(rectangle, start, menu.getMusicBtn());
        menu.getMusicBtn().setLayoutX((double) Width / 2 + recWidth / 2 - 50);
        menu.getMusicBtn().setLayoutY((double) Height / 2 - recHeight / 2);

        Timeline ballAnimation = new Timeline(new KeyFrame(Duration.millis(16.5), e -> {
            mBall.move();
        }));

        ballAnimation.setCycleCount(Timeline.INDEFINITE);
        ballAnimation.play();

        start.setOnMouseClicked(e -> {
            setPlay(true);
            addMenu = true;
        });
    }

    public boolean getPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
        this.move = true;
    }

    public boolean isAddMenu() {
        return addMenu;
    }

    public boolean isMove() {
        return move;
    }
}
