package com.example.air_hockey_with_java;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;


public class Menu extends Pane {
    private boolean mute = false;
    private boolean close = false;
    Font fnt = Font.font("Time New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);

    boolean resetValue = false;

    Menu() throws FileNotFoundException {
        // music Button
        Button musicBtn = new Button();
        Image muteimg = new Image(new FileInputStream("images\\mute.png"));
        ImageView muteview = new ImageView(muteimg);
        Image unmuteimg = new Image(new FileInputStream("images\\unmute.png"));
        ImageView unmuteview = new ImageView(unmuteimg);
        muteview.setFitHeight(30);
        muteview.setFitWidth(30);
        unmuteview.setFitHeight(30);
        unmuteview.setFitWidth(30);
        musicBtn.setGraphic(unmuteview);
        musicBtn.setStyle("-fx-background-color: transparent ;");

        // Close  Button
        Button closeBtn = new Button();
        Image closeImg = new Image(new FileInputStream("images\\close64.png"));
        ImageView closeView = new ImageView(closeImg);
        closeView.setFitHeight(40);
        closeView.setFitWidth(40);
        closeBtn.setGraphic(closeView);
        closeBtn.setStyle("-fx-background-color: transparent ;");

        //Reset Button
        Button resetBtn = new Button("Reset");
        resetBtn.setPrefSize(100, 25);
        resetBtn.setTextFill(Color.WHITE);
        resetBtn.setStyle("-fx-background-color:  transparent;" + "-fx-border-color:  rgb(15, 240, 252 , .6);" + "-fx-border-radius:50;" + "-fx-cursor: pointer;");
        resetBtn.setFont(fnt);
        double recWidth = Width - 100;
        double recHeight = Height - 550;

        //Create a Rectangle to add button on it
        Rectangle rectangle = new Rectangle(recWidth, recHeight);
        rectangle.setLayoutX(Width / 2 - recWidth / 2);
        rectangle.setLayoutY(Height / 2 - recHeight / 2);
        rectangle.setStyle("-fx-border-radius:50;");
        rectangle.setFill(Color.rgb(15, 240, 252, .2));
        rectangle.setStroke(Color.rgb(15, 240, 252));
        rectangle.setStrokeWidth(5);

        //add elements to pane
        this.setPrefSize(Width, Height);
        this.setStyle("-fx-background-color:  rgb(0, 0, 0 , .6);");
        this.getChildren().addAll(rectangle, musicBtn, resetBtn, closeBtn);

        // organise buttons
        resetBtn.setLayoutY(Height / 2 - 20);
        resetBtn.setLayoutX(Width / 2 - 50);
        musicBtn.setLayoutX(recWidth - 5);
        musicBtn.setLayoutY(Height - 2 * recHeight - 125);
        closeBtn.setLayoutX(Width - recWidth - 55);
        closeBtn.setLayoutY(Height - 2 * recHeight - 127);


        musicBtn.setOnAction(e -> {
            mute = !mute;
            if (mute) {
                musicBtn.setGraphic(muteview);
            } else {
                musicBtn.setGraphic(unmuteview);
            }
        });

        resetBtn.setOnAction(e -> {
            reset();
        });

        closeBtn.setOnAction(e -> {
            close();
        });
    }

    public void reset() {
        resetValue = true;
    }

    public void close() {
        close = true;
    }

    public boolean isClose() {
        return close;
    }

    public boolean isResetValue() {
        return resetValue;
    }

    public void setResetValue(boolean resetValue) {
        this.resetValue = resetValue;
    }
}
