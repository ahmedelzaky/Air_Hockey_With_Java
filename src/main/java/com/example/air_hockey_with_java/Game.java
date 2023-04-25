package com.example.air_hockey_with_java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            double x1 = p1.getCenterX();
            double y1 = p1.getCenterY();
            double radius1 = p1.getRadius();

            double x2 = p2.getCenterX();
            double y2 = p2.getCenterY();
            double radius2 = p2.getRadius();

            double step = 5;

            // Move both players at the same time with different keys
            if (event.isShiftDown()) {
                switch (event.getCode()) {
                    case UP:
                        if (y1 - step >= radius1) {
                            y1 -= step;
                        }
                        break;
                    case DOWN:
                        if (y1 + step <= (double) Height / 2 - radius1) {
                            y1 += step;
                        }
                        break;
                    case LEFT:
                        if (x1 - step >= radius1) {
                            x1 -= step;
                        }
                        break;
                    case RIGHT:
                        if (x1 + step <= Width - radius1) {
                            x1 += step;
                        }
                        break;
                    case W:
                        if (y2 - step >= radius2) {
                            y2 -= step;
                        }
                        break;
                    case S:
                        if (y2 + step <= Height - radius2) {
                            y2 += step;
                        }
                        break;
                    case A:
                        if (x2 - step >= radius2) {
                            x2 -= step;
                        }
                        break;
                    case D:
                        if (x2 + step <= Width - radius2) {
                            x2 += step;
                        }
                        break;
                    default:
                        break;
                }
            }
            // Move only one player at a time
            else {
                switch (event.getCode()) {
                    case UP:
                        if (y1 - step >= radius1) {
                            y1 -= step;
                        }
                        break;
                    case DOWN:
                        if (y1 + step <= (double) Height / 2 - radius1) {
                            y1 += step;
                        }
                        break;
                    case LEFT:
                        if (x1 - step >= radius1) {
                            x1 -= step;
                        }
                        break;
                    case RIGHT:
                        if (x1 + step <= Width - radius1) {
                            x1 += step;
                        }
                        break;
                    case W:
                        if (y2 - step >= (double) Height / 2 + radius2) {
                            y2 -= step;
                        }
                        break;
                    case S:
                        if (y2 + step <= Height - radius2) {
                            y2 += step;
                        }
                        break;
                    case A:
                        if (x2 - step >= radius2) {
                            x2 -= step;
                        }
                        break;
                    case D:
                        if (x2 + step <= Width - radius2) {
                            x2 += step;
                        }
                        break;
                    default:
                        break;
                }
            }

            p1.setCenterX(x1);
            p1.setCenterY(y1);

            p2.setCenterX(x2);
            p2.setCenterY(y2);
        });


    }

    public static void main(String[] args) {
        launch();
    }


}