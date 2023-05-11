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
    private boolean mini = true;


    private boolean MiniClicked = false;
    private Font fnt = Font.font("Time New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);

    private boolean resetValue = false;


    Menu() throws FileNotFoundException {
        // music Button
        Button musicBtn = new Button();
        Image muteImg = new Image(new FileInputStream("images\\mute.png"));
        ImageView muteView = new ImageView(muteImg);
        Image unmuteImg = new Image(new FileInputStream("images\\unmute.png"));
        ImageView unmuteView = new ImageView(unmuteImg);
        muteView.setFitHeight(30);
        muteView.setFitWidth(30);
        unmuteView.setFitHeight(30);
        unmuteView.setFitWidth(30);
        musicBtn.setGraphic(unmuteView);
        musicBtn.setStyle("-fx-background-color: transparent ;");

        // Close  Button
        Button closeBtn = new Button();
        Image closeImg = new Image(new FileInputStream("images\\close64.png"));
        ImageView closeView = new ImageView(closeImg);
        closeView.setFitHeight(30);
        closeView.setFitWidth(30);
        closeBtn.setGraphic(closeView);
        closeBtn.setStyle("-fx-background-color: transparent ;");

        // minimize  Button
        Button miniBtn = new Button();
        Image miniImg = new Image(new FileInputStream("images\\minimize64.png"));
        ImageView miniView = new ImageView(miniImg);
        miniView.setFitHeight(30);
        miniView.setFitWidth(30);
        miniBtn.setGraphic(miniView);
        miniBtn.setStyle("-fx-background-color: transparent ;");

        //Restart Button
        Button resetBtn = new Button("Restart");
        resetBtn.setPrefSize(100, 25);
        resetBtn.setTextFill(Color.WHITE);
        resetBtn.setStyle("-fx-background-color:  transparent;" + "-fx-border-color:  rgb(200, 34, 255 , .6);" + "-fx-border-radius:50;" + "-fx-cursor: pointer;");
        resetBtn.setFont(fnt);
        double recWidth = Width - 100;
        double recHeight = Height - 550;

        //Create a Rectangle to add button on it
        Rectangle rectangle = new Rectangle(recWidth, recHeight);
        rectangle.setLayoutX((double) Width / 2 - recWidth / 2);
        rectangle.setLayoutY((double) Height / 2 - recHeight / 2);
        rectangle.setStyle("-fx-border-radius:50;");
        rectangle.setFill(Color.rgb(200, 34, 255, .2));
        rectangle.setStroke(Color.rgb(200, 34, 255, .8));
        rectangle.setStrokeWidth(5);

        //add elements to pane
        this.setPrefSize(Width, Height);
        this.setStyle("-fx-background-color:  rgb(0, 0, 0 , .6);");
        this.getChildren().addAll(rectangle, musicBtn, resetBtn, closeBtn, miniBtn);

        // organise buttons
        resetBtn.setLayoutY((double) Height / 2 - 20);
        resetBtn.setLayoutX((double) Width / 2 - 50);
        musicBtn.setLayoutX(recWidth - 5);
        musicBtn.setLayoutY(Height - 2 * recHeight - 125);
        closeBtn.setLayoutX(Width - recWidth - 55);
        closeBtn.setLayoutY(Height - 2 * recHeight - 125);
        miniBtn.setLayoutX(Width - recWidth - 27);
        miniBtn.setLayoutY(Height - 2 * recHeight - 125);


        musicBtn.setOnAction(e -> {
            mute = !mute;
            if (mute) {
                musicBtn.setGraphic(muteView);
            } else {
                musicBtn.setGraphic(unmuteView);
            }
        });

        resetBtn.setOnAction(e -> {
            reset();
        });

        closeBtn.setOnAction(e -> {
            close();
        });
        miniBtn.setOnAction(e -> {
            minimize();
            MiniClicked = true;
        });
    }

    public void reset() {
        resetValue = true;
    }

    public void close() {
        close = true;
    }

    public void minimize() {
        mini = true;
    }

    public boolean isClose() {
        return close;
    }

    public boolean isMinimize() {
        return mini;
    }

    public boolean isMiniClicked() {
        return MiniClicked;
    }

    public void setMini(boolean mini) {
        this.mini = mini;
    }

    public boolean isResetValue() {
        return resetValue;
    }



    public void setResetValue(boolean resetValue) {
        this.resetValue = resetValue;
    }
}
