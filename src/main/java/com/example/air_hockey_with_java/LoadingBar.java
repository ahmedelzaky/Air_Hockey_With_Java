package com.example.air_hockey_with_java;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;

public class LoadingBar extends BorderPane {

    private Label label = new Label("Loading...");
    private ProgressBar loadingBar = new ProgressBar();

    private double loadingP = .01;
    private Timeline loadingAnimation;

    LoadingBar() {
        this.setPrefSize(Width, Height);

        loadingBar.setProgress(0);
        loadingBar.setPrefSize(Width - 50, 20);
        loadingBar.setStyle("-fx-accent: #FF3131 ");
        loadingAnimation = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            loadingBar.setProgress(loadingP);
            loadingP += .01;
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

        return loadingP >= 1;
    }

    public void setLoadingP(double loadingP) {
        this.loadingP = loadingP;
    }
}

