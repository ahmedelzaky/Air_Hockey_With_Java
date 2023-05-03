package com.example.air_hockey_with_java;

import javafx.scene.Scene;

public class PlayerThread extends Thread {
    private Player player;
    private Scene scene;

    public PlayerThread(Player player, Scene scene) {
        this.player = player;
        this.scene = scene;
    }

    @Override
    public void run() {
        for(;;){
        scene.setOnKeyPressed(e -> {

                    player.keyPressed(e);

        });
    }
        }

}
