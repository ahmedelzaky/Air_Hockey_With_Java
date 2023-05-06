package com.example.air_hockey_with_java;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;

public class PlayerThread extends Thread {
    private Player player;
    private Scene scene;
    private ScheduledExecutorService executor;

    public PlayerThread(Player player, Scene scene) {
        this.player = player;
        this.scene = scene;
    }

    @Override
    public void run() {
        // Create a scheduled executor service with a fixed delay of 10 milliseconds
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::updatePlayerPosition, 0, 10, TimeUnit.MILLISECONDS);

        // Register a key released event handler for the scene
        scene.setOnKeyPressed(event -> {
            double x = player.getCenterX();
            double y = player.getCenterY();
            int step = 5;

            if (player.getPlayerId() == 1) {
                if (event.isShiftDown()) {
                    step = 10;
                }
                switch (event.getCode()) {
                    case UP:
                        if (y - step >= player.getRadius()) {
                            y -= step;
                        }
                        break;
                    case DOWN:
                        if (y + step <= (double) Height / 2) {
                            y += step;
                        }
                        break;
                    case LEFT:
                        if (x - step >= player.getRadius()) {
                            x -= step;
                        }
                        break;
                    case RIGHT:
                        if (x + step <= Width - player.getRadius()) {
                            x += step;
                        }
                        break;
                    default:
                        break;

                }
                player.setCenterY(y);
                player.setCenterX(x);
            }
            else if (player.getPlayerId() == 2) {
                if (event.isControlDown()) {
                    step = 10;
                }
                switch (event.getCode()) {
                    case W:
                        if (y - step >= (double) Height / 2) {
                            y -= step;
                        }
                        break;
                    case S:
                        if (y + step <= Height - player.getRadius()) {
                            y += step;
                        }
                        break;
                    case A:
                        if (x - step >= player.getRadius()) {
                            x -= step;
                        }
                        break;
                    case D:
                        if (x + step <= Width - player.getRadius()) {
                            x += step;
                        }
                        break;
                    default:
                        break;
                }
                player.setCenterY(y);
                player.setCenterX(x);
            }
        });
    }

    private void updatePlayerPosition() {
        // Update the player position on the JavaFX application thread
        scene.getRoot().requestFocus();
    }

    public void stopThread() {
        // Stop the executor service when the thread is stopped
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
