package com.example.air_hockey_with_java;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;

public class LoadingBar extends BorderPane {

    Label label = new Label("Loading...");
    javafx.scene.control.ProgressBar loadingBar = new javafx.scene.control.ProgressBar();

    double i = .01;
    Timeline loadingAnimation;

    LoadingBar() {
        this.setPrefSize(Width, Height);

        loadingBar.setProgress(0);
        loadingBar.setPrefSize(Width - 50, 20);
        loadingBar.setStyle("-fx-accent: #FF3131 ");
        loadingAnimation = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            loadingBar.setProgress(i);
            i += .01;
            System.out.println(i);
        })
        );
        loadingAnimation.setCycleCount(100);
        HBox hbox = new HBox();
        hbox.getChildren().add(label);
        hbox.setAlignment(Pos.CENTER);
        hbox.setTranslateY(-300);
        label.setFont(Font.font("Arial", 18));

        this.setCenter(loadingBar);
        this.setBottom(hbox);
        this.setStyle("-fx-background-color : black;");
        label.setTextFill(Color.RED);


    }


    public void animationPlay() {
        loadingAnimation.play();
    }

    public boolean finish() {

        return i >= 1;
    }
}

