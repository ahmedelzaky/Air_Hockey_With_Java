package com.example.air_hockey_with_java;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;

public class Menu extends Pane {
    boolean mute = false;
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

        Button resetBtn = new Button("Reset");
        resetBtn.setPrefSize(100, 20);
        this.setPrefSize(Width - 100, Height - 400);
        resetBtn.setTextFill(Color.WHITE);
        resetBtn.setStyle("-fx-background-color:  transparent;" + " -fx-font-size: 18px; " + "-fx-border-color:  rgb(5, 195, 221 , .6);" + "-fx-border-radius:50;" + "-fx-cursor: pointer;");


        this.getChildren().addAll(musicBtn, resetBtn);
        resetBtn.setLayoutY((double) (Height - 400) / 2 - 15);
        resetBtn.setLayoutX((double) (Width - 100) / 2 - 50);
        musicBtn.setLayoutX((Width - 100) - 60);

        this.setLayoutX((double) Width / 2);
        this.setLayoutY((double) Height / 2);
        this.setTranslateX((double) -(Width - 100) / 2);
        this.setTranslateY((double) -(Height - 400) / 2);

        this.setStyle("-fx-background-color: rgb(5, 195, 221 , .3); " + "-fx-border-radius:50;");
        this.setBorder(Border.stroke(Color.GREEN));

        musicBtn.setOnAction(e -> {
            mute = !mute;
            if (mute) {
                musicBtn.setGraphic(muteview);
            } else {
                musicBtn.setGraphic(unmuteview);
            }
        });

        resetBtn.setOnAction(e ->{
            reset();
        });
    }
    public void reset( ) {
        resetValue = true;
    }

    public boolean isResetValue() {
        return resetValue;
    }

    public void setResetValue(boolean resetValue) {
        this.resetValue = resetValue;
    }
}
