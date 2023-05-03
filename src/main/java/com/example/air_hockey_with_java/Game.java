package com.example.air_hockey_with_java;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.RadialGradient;
import javafx.stage.Stage;


public class Game extends Application  {
    final static int Height = 700;
    final static int Width = (int) (Height * 0.5);
    Player p1 = new Player(30, 1, 90, 0, 255, 255);
    Player p2 = new Player(30, 2, Height - 90, 102, 255, 51);
    Ball ball = new Ball();




    @Override
    public void start(Stage stage) {

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
   /*     PlayerThread player1Thread = new PlayerThread(p1,scene);
        PlayerThread player2Thread = new PlayerThread(p2,scene);
        player1Thread.start();
        player2Thread.start();
        System.out.println(Thread.activeCount());
        System.out.println(Thread.currentThread());*/

        scene.setOnKeyPressed(e -> {
             p1.keyPressed(e);
            p2.keyPressed(e);
        });


    }


//    public void run() {
//        //game loop
//        long lastTime = System.nanoTime();
//        double amountOfTicks = 60.0;
//        double ns = 1000000000 / amountOfTicks;
//        double delta = 0;
//        while (true) {
//            long now = System.nanoTime();
//            delta += (now - lastTime) / ns;
//            lastTime = now;
//            if (delta >= 1) {
//                checkCollision();
//                delta--;
//            }
//        }
//    }
//
//    public void checkCollision() {
//
//        //bounce ball off top & bottom window edges
//        if (ball.getCenterY() <= 0) {
//            ball.setyVelocity(-ball.getyVelocity());
//        }
//        if (ball.getCenterY() >= Height - 2 * ball.getRadius()) {
//            ball.setyVelocity(-ball.getyVelocity());
//        }
//        //bounce ball off paddles
//            /*if(ball.intersects(paddle1)) {
//                ball.xVelocity = Math.abs(ball.xVelocity);
//                ball.xVelocity++; //optional for more difficulty
//                if(ball.yVelocity>0)
//                    ball.yVelocity++; //optional for more difficulty
//                else
//                    ball.yVelocity--;
//                ball.setXDirection(ball.xVelocity);
//                ball.setYDirection(ball.yVelocity);
//            }
//            if(ball.intersects(paddle2)) {
//                ball.xVelocity = Math.abs(ball.xVelocity);
//                ball.xVelocity++; //optional for more difficulty
//                if(ball.yVelocity>0)
//                    ball.yVelocity++; //optional for more difficulty
//                else
//                    ball.yVelocity--;
//                ball.setXDirection(-ball.xVelocity);
//                ball.setYDirection(ball.yVelocity);
//            }*/
//    }

        public static void main (String[]args){
            launch();
        }
}