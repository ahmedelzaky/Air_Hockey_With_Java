package com.example.air_hockey_with_java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Game extends Application {
    final static int Height = 700;
    final static int Width = (int) (Height * 0.5);

    @Override
    public void start(Stage stage) {
        player p1 = new player(1, 90, 0, 255, 255);
        player p2 = new player(2, Height - 90, 102, 255, 51);
        Ball ball = new Ball();
        GameFrame game = new GameFrame();

        game.getChildren().add(p1);
        game.getChildren().add(p2);
        game.getChildren().add(ball);

        Scene scene = new Scene(game, Width, Height);

        // Add the icon to the list of icons for the stage
//        Image icon = new Image(new FileInputStream("D:\\study\\java\\air_hockey_with_java\\src\\main\\java\\com\\example\\air_hockey_with_java\\icon.png"));
//        stage.getIcons().add(icon);
        stage.setTitle("Air Hockey Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

   /*     scene.setOnMouseMoved(e -> {
            p1.setCenterY(e.getY());
            p1.setCenterX(e.getX());
        });*/
        scene.setOnKeyPressed(e -> {
            p1.keyPreesd(e);
            p2.keyPreesd(e);
        });

    }

    public static void main(String[] args) {
        launch();
    }


}