package com.example.air_hockey_with_java;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Game extends Application {
    final static int Height = 700;
    final static int Width = (int) (Height * 0.5);
    Player p1 = new Player(30, 1, 90, 57, 255, 20);
    Player p2 = new Player(30, 2, Height - 90, 15, 240, 252);
    Ball ball = new Ball();
    Text p1Score = new Text();
    Text p2Score = new Text();
    private Timeline ballAnimation;
    private Timeline playerAnimation;

    private Timeline resetChecker;
    private boolean clicked = false;
    Menu menu = new Menu();
    GameFrame game = new GameFrame();

    LoadingBar progressBar = new LoadingBar();

    public Game() throws FileNotFoundException {
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {

        game.getChildren().add(p1);
        game.getChildren().add(p2);
        game.getChildren().add(ball);

        p1Score.setText(String.valueOf(p1.getScore()));
        p2Score.setText(String.valueOf(p2.getScore()));
        p1Score.setFill(Color.RED);
        p2Score.setFill(Color.BLUE);
        p1Score.setFont(Font.font("Arial", 30));
        p2Score.setFont(Font.font("Arial", 30));
        VBox conter = new VBox(5);
        conter.setPrefWidth(150);
        conter.setLayoutX(Width - 30);
        conter.setLayoutY(Height / 2 - 65);
        // menu Button
        Button menuBtn = new Button();
        Image menuimg = new Image(new FileInputStream("images\\menu64.png"));
        ImageView menueview = new ImageView(menuimg);
        menueview.setFitHeight(45);
        menueview.setFitWidth(45);


        menuBtn.setGraphic(menueview);
        menuBtn.setLayoutY(Height / 2);
        menuBtn.setTranslateX(-25);


        menuBtn.setStyle("-fx-background-color: transparent;");
        p1Score.setTranslateY(5);
        conter.getChildren().addAll(p1Score, menuBtn, p2Score);


        game.getChildren().add(conter);

        game.getChildren().add(progressBar);
        progressBar.animationPlay();


        Scene scene = new Scene(game, Width, Height);

        // Add the icon to the list of icons for the stage
        Image icon = new Image(new FileInputStream("images\\icon.png"));
        stage.getIcons().add(icon);

/*
        Media media = new Media("file:///path/to/audio/file.mp3");

        // Create a MediaPlayer with the Media instance
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // Play the audio
        mediaPlayer.play();
*/


        stage.setTitle("Air Hockey Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


   /*     scene.setOnMouseMoved(e -> {
            p1.setCenterY(e.getY());
            p1.setCenterX(e.getX());
        });*/

        scene.setOnKeyPressed(e -> {
            p1.keyPressed(e);
            p2.keyPressed(e);
        });
        scene.setOnKeyReleased(e -> {
            p1.keyReleased(e);
            p2.keyReleased(e);
        });
        //toggle menu button
        menuBtn.setOnAction(e -> {
            isClicked();
        });


        playerAnimation = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            p1.Move();
            p2.Move();
        })
        );
        playerAnimation.setCycleCount(Timeline.INDEFINITE);


        ballAnimation = new Timeline(new KeyFrame(Duration.millis(13), e -> {
            checkCollision();
        }));
        ballAnimation.setCycleCount(Timeline.INDEFINITE);


        Timeline loading = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            loadingAnimation();
        }));
        loading.setCycleCount(Timeline.INDEFINITE);
        loading.play();

        Timeline resetChecker = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            gameReset();
        }));
        resetChecker.setCycleCount(Timeline.INDEFINITE);
        resetChecker.play();

    }

    public void checkCollision() {
        p2Score.setText(String.valueOf(p2.getScore()));

        //  System.out.println(p1.getScore() + " : " + p2.getScore());
        if (ball.getCenterX() < Width / 2 + game.getArcRaduis() && ball.getCenterX() > Width / 2 - game.getArcRaduis() && ball.getCenterY() - ball.getRadius() <= 0) {
            try {
                Thread.sleep(1000); // sleep for 10 milliseconds
            } catch (InterruptedException e) {
                // handle interrupted exception
            }
            p2.addPoint();
            //update counter

            ball.rest(Height / 2 - 50);
        }
        if (ball.getCenterX() < Width / 2 + game.getArcRaduis() && ball.getCenterX() > Width / 2 - game.getArcRaduis() && ball.getCenterY() + ball.getRadius() >= Height) {
            try {
                Thread.sleep(1000); // sleep for 10 milliseconds
            } catch (InterruptedException e) {
                // handle interrupted exception
            }
            p1.addPoint();
            //update counter
            p1Score.setText(String.valueOf(p1.getScore()));
            ball.rest(Height / 2 + 50);
        }
        // check if the ball hit the top or bottom side
        if (ball.getCenterY() - ball.getRadius() <= 0 || ball.getCenterY() >= Height - ball.getRadius()) {
            ball.setyVelocity(-ball.getyVelocity());
        }
        // check if the ball hit the right or left side
        if (ball.getCenterX() - ball.getRadius() <= 0 || ball.getCenterX() >= Width - ball.getRadius()) {
            ball.setxVelocity(-ball.getxVelocity());
        }

        if (ball.intersects(p1)) {
            //check if the ball hit the player in the middle
            if (ball.getCenterX() - p1.getCenterX() == 0) {
                //check if the ball hit the player from behind
                if (ball.getCenterY() < p1.getCenterY()) {
                    ball.setxVelocity(0);
                    ball.setyVelocity(-p1.getyVelocity());
                } else {
                    ball.setxVelocity(0);
                    ball.setyVelocity(p1.getyVelocity());
                }
            } //check if the ball hit player left side
            else if (ball.getCenterX() < p1.getCenterX()) {
                //check if the ball hit the player from behind
                if (ball.getCenterY() < p1.getCenterY()) {
                    ball.setxVelocity(-p1.getxVelocity());
                    ball.setyVelocity(-p1.getyVelocity());
                } else {
                    ball.setxVelocity(-p1.getxVelocity());
                    ball.setyVelocity(p1.getyVelocity());
                }


            } else {
                //check if the ball hit the player from behind
                if (ball.getCenterY() < p1.getCenterY()) {
                    ball.setxVelocity(p1.getxVelocity());
                    ball.setyVelocity(-p1.getyVelocity());
                } else {
                    ball.setxVelocity(p1.getxVelocity());
                    ball.setyVelocity(p1.getyVelocity());
                }

            }

        }

        if (ball.intersects(p2)) {

            if (ball.getCenterX() - p2.getCenterX() == 0) {
                //check if the ball hit the player from behind
                if (ball.getCenterY() > p2.getCenterY()) {
                    ball.setxVelocity(0);
                    ball.setyVelocity(p2.getyVelocity());
                } else {
                    ball.setxVelocity(0);
                    ball.setyVelocity(-p2.getyVelocity());
                }
            }  //check if the ball hit player left side
            else if (ball.getCenterX() < p2.getCenterX()) {
                //check if the ball hit the player from behind
                if (ball.getCenterY() > p2.getCenterY()) {
                    ball.setxVelocity(-p2.getxVelocity());
                    ball.setyVelocity(p2.getyVelocity());
                } else {
                    ball.setxVelocity(-p2.getxVelocity());
                    ball.setyVelocity(-p2.getyVelocity());
                }

            } else {
                //check if the ball hit the player from behind
                if (ball.getCenterY() > p2.getCenterY()) {
                    ball.setxVelocity(p2.getxVelocity());
                    ball.setyVelocity(p2.getyVelocity());
                } else {
                    ball.setxVelocity(p2.getxVelocity());
                    ball.setyVelocity(-p2.getyVelocity());
                }
            }
        }
        // move the ball
        ball.move();
    }

    public void isClicked() {
        if (!clicked) {
            game.getChildren().add(menu);
            clicked = !clicked;
            playerAnimation.pause();
            ballAnimation.pause();

        } else {
            game.getChildren().remove(menu);
            clicked = !clicked;
            playerAnimation.play();
            ballAnimation.play();
        }
    }

    boolean visited = false;

    public void loadingAnimation() {
        if (progressBar.finish() && !visited) {
            game.getChildren().remove(progressBar);
            playerAnimation.play();
            ballAnimation.play();
            visited = !visited;

        }

    }

    public void gameReset() {
        if (menu.isResetValue()) {
            menu.setResetValue(false);
            p1.rest();
            p2.rest();
            p1Score.setText(String.valueOf(p1.getScore()));
            p2Score.setText(String.valueOf(p2.getScore()));
            ball.rest(Height / 2);
            game.getChildren().remove(menu);
            clicked = !clicked;
            playerAnimation.play();
            ballAnimation.play();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}