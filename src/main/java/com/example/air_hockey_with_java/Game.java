package com.example.air_hockey_with_java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;


public class Game extends Application {
    final static int Height = 700;
    final static int Width = (int) (Height * 0.5);

    @Override
    public void start(Stage stage) throws IOException {
        player p1 = new player(1, 90, 63, 168, 107);
        player p2 = new player(2, Height - 90, 84, 51, 255);
        Ball ball = new Ball();
        GameFrame game = new GameFrame();

        game.getChildren().add(p1);
        game.getChildren().add(p2);
        game.getChildren().add(ball);

        Scene scene = new Scene(game, Width, Height);

        // Add the icon to the list of icons for the stage
        Image icon = new Image(new FileInputStream("D:\\study\\java\\air_hockey_with_java\\src\\main\\java\\com\\example\\air_hockey_with_java\\icon.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Air Hockey Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

//        scene.setOnMouseMoved(e->{
//            p1.setCenterY(e.getY());
//            p1.setCenterX(e.getX());
//        });
        scene.setOnKeyPressed(e -> {
            p1.keyPreesd(e);
            p2.keyPreesd(e);
        });

    }

    public static void main(String[] args) {
        launch();
    }


}