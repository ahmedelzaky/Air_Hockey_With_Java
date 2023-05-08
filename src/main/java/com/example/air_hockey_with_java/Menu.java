package com.example.air_hockey_with_java;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Menu extends FlowPane {
    boolean mute = false;

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
        musicBtn.setStyle("-fx-background-color: black;");

        musicBtn.setOnAction(e -> {
            mute = !mute;
            if (mute) {
                musicBtn.setGraphic(muteview);
            } else {
                musicBtn.setGraphic(unmuteview);
            }
        });
    }


}
