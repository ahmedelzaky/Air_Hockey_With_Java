package com.example.air_hockey_with_java;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Game extends Application {
    protected final static int Height = 700;
    protected final static int Width = (int) (Height * 0.5);
    private double angle;
    private int frameTime = 5;
    private Player p1 = new Player(30, 1, 90, 57, 255, 20);
    private Player p2 = new Player(30, 2, Height - 90, 15, 240, 252);
    private Ball ball = new Ball();
    private Text p1Score = new Text();
    private Text p2Score = new Text();

    private Font fnt = Font.font("Time New Roman", FontWeight.BOLD, FontPosture.ITALIC, 40);
    private Timeline ballAnimation;
    private Timeline playerAnimation;
    private boolean clicked = false;
    private Menu menu = new Menu();
    private GameFrame game = new GameFrame();

    private GaussianBlur blurEffect = new GaussianBlur();

    private Pane mainPane;

    private LoadingScreen loadingScreen = new LoadingScreen();


    public Game() throws FileNotFoundException {
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {

        game.getChildren().add(p1);
        game.getChildren().add(p2);
        game.getChildren().add(ball);

        p1Score.setText(String.valueOf(p1.getScore()));
        p2Score.setText(String.valueOf(p2.getScore()));
        p1Score.setStroke(Color.RED);
        p2Score.setStroke(Color.BLUE);
        p1Score.setFont(fnt);
        p2Score.setFont(fnt);
        VBox counter = new VBox(5);
        counter.setPrefWidth(140);
        counter.setLayoutX(Width - 35);
        counter.setLayoutY((double) Height / 2 - 85);
        // menu Button
        Button menuBtn = new Button();
        Image menuImg = new Image(new FileInputStream("images\\menu64.png"));
        ImageView menuView = new ImageView(menuImg);
        menuView.setFitHeight(45);
        menuView.setFitWidth(45);


        menuBtn.setGraphic(menuView);
        menuBtn.setTranslateX(-25);
        menuBtn.setStyle("-fx-background-color: transparent;");

        counter.getChildren().addAll(p1Score, menuBtn, p2Score);
        game.getChildren().add(counter);
        game.getChildren().add(loadingScreen);
        loadingScreen.animationPlay();


        mainPane = new Pane();
        mainPane.getChildren().add(game);

        Scene scene = new Scene(mainPane, Width, Height);

        // Add the icon to the list of icons for the stage
        Image icon = new Image(new FileInputStream("images\\icon.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Air Hockey Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


//       scene.setOnMouseMoved(e -> {
//            ball.setCenterY(e.getY());
//            ball.setCenterX(e.getX());
//        });

        scene.setOnKeyPressed(e -> {
            p1.keyPressed(e);
            p2.keyPressed(e);
        });
        scene.setOnKeyReleased(e -> {
            p1.keyReleased(e);
            p2.keyReleased(e);
        });
        //toggle menu
        game.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                isClicked();
            }
        });
        menuBtn.setOnMouseClicked(e -> isClicked());


        playerAnimation = new Timeline(new KeyFrame(Duration.millis(frameTime), e -> {
            p1.Move();
            p2.Move();
        }));
        playerAnimation.setCycleCount(Timeline.INDEFINITE);


        ballAnimation = new Timeline(new KeyFrame(Duration.millis(frameTime), e -> checkCollision()));
        ballAnimation.setCycleCount(Timeline.INDEFINITE);


        Timeline loading = new Timeline(new KeyFrame(Duration.millis(frameTime), e -> loadingAnimation()));
        loading.setCycleCount(200);
        loading.play();

        Timeline resetChecker = new Timeline(new KeyFrame(Duration.millis(frameTime), e -> gameReset()));
        resetChecker.setCycleCount(Timeline.INDEFINITE);
        resetChecker.play();

        Timeline gameOver = new Timeline(new KeyFrame(Duration.millis(frameTime), e -> checkScore()));
        gameOver.setCycleCount(Timeline.INDEFINITE);
        gameOver.play();

        Timeline closeAndMiniChecker = new Timeline(new KeyFrame(Duration.millis(frameTime), e -> {
            closeCheck(stage);
            miniCheck();
        }));
        closeAndMiniChecker.setCycleCount(Timeline.INDEFINITE);
        closeAndMiniChecker.play();

    }

    public void checkCollision() {
        game.requestFocus();
        //  System.out.println(p1.getScore() + " : " + p2.getScore());
        if (ball.getCenterX() < (double) Width / 2 + game.getArcRadius() - ball.getRadius() && ball.getCenterX() > (double) Width / 2 - game.getArcRadius() + ball.getRadius() && ball.getCenterY() - ball.getRadius() <= 0) {
            try {
                Thread.sleep(500); // sleep for .5 seconds
            } catch (InterruptedException e) {
                // handle interrupted exception
            }
            p2.addPoint();
            //update counter
            p2Score.setText(String.valueOf(p2.getScore()));
            //reset players and ball
            if (p2.getScore() < 7) {
                p1.rest();
                p2.rest();
                ball.rest(Height / 2 - 50);
            }

        }
        if (ball.getCenterX() < (double) Width / 2 + game.getArcRadius() - ball.getRadius() && ball.getCenterX() > (double) Width / 2 - game.getArcRadius() + ball.getRadius() && ball.getCenterY() + ball.getRadius() >= Height) {
            try {
                Thread.sleep(500); // sleep for .5 seconds
            } catch (InterruptedException e) {
                // handle interrupted exception
            }
            p1.addPoint();
            //update counter
            p1Score.setText(String.valueOf(p1.getScore()));
            //reset players and ball
            if (p1.getScore() < 7) {
                p1.rest();
                p2.rest();
                ball.rest(Height / 2 + 50);
            }
        }
        // check if the ball hit the top or bottom side
        if (ball.getCenterY() - ball.getRadius() <= 0 || ball.getCenterY() >= Height - ball.getRadius()) {
            ball.setYVelocity(-ball.getYVelocity());
        }
        // check if the ball hit the right or left side
        if (ball.getCenterX() - ball.getRadius() <= 0 || ball.getCenterX() >= Width - ball.getRadius()) {
            ball.setXVelocity(-ball.getXVelocity());
        }
        if (ball.intersects(p1)) {
            p1.hit();
            //check if the ball hit the player in the middle
            if (ball.getCenterX() - p1.getCenterX() == 0) {
                //check if the ball hit the player from behind
                if (ball.getCenterY() < p1.getCenterY()) {
                    ball.setXVelocity(0);
                    ball.setYVelocity(-p1.getVelocity());
                } else {
                    ball.setXVelocity(0);
                    ball.setYVelocity(p1.getVelocity());
                }
            } else {
                // calc angle of collision
                angle = Math.atan((ball.getCenterY() - p1.getCenterY()) / (ball.getCenterX() - p1.getCenterX()));

                //check if the ball hit the player from left side
                if (ball.getCenterX() < p1.getCenterX()) {
                    ball.setXVelocity(-(Math.cos(angle) * p1.getVelocity()));
                    ball.setYVelocity(-(Math.sin(angle) * p1.getVelocity()));
                } else {
                    ball.setXVelocity((Math.cos(angle) * p1.getVelocity()));
                    ball.setYVelocity((Math.sin(angle) * p1.getVelocity()));
                }

            }
        }
        if (ball.intersects(p2)) {
            p2.hit();

            if (ball.getCenterX() - p2.getCenterX() == 0) {
                //check if the ball hit the player from behind
                if (ball.getCenterY() > p2.getCenterY()) {
                    ball.setXVelocity(0);
                    ball.setYVelocity(p2.getVelocity());
                } else {
                    ball.setXVelocity(0);
                    ball.setYVelocity(-p2.getVelocity());
                }
            } else {
                // calc angle of collision
                angle = Math.atan((ball.getCenterY() - p2.getCenterY()) / (ball.getCenterX() - p2.getCenterX()));
                //check if the ball hit the player from left side
                if (ball.getCenterX() < p2.getCenterX()) {
                    ball.setXVelocity(-(Math.cos(angle) * p2.getVelocity()));
                    ball.setYVelocity(-(Math.sin(angle) * p2.getVelocity()));
                } else {
                    ball.setXVelocity((Math.cos(angle) * p2.getVelocity()));
                    ball.setYVelocity((Math.sin(angle) * p2.getVelocity()));
                }

            }
        }
        // move the ball
        ball.move();
    }

    public void isClicked() {
        if (!clicked) {
            clicked = true;
            game.setEffect(blurEffect);
            mainPane.getChildren().add(menu);
            playerAnimation.pause();
            ballAnimation.pause();

        } else {
            clicked = false;
            game.setEffect(null);
            mainPane.getChildren().remove(menu);
            playerAnimation.play();
            ballAnimation.play();
        }

    }

    public void loadingAnimation() {
        if (loadingScreen.finish()) {
            game.getChildren().remove(loadingScreen);
            playerAnimation.play();
            ballAnimation.play();
            loadingScreen.setLoadingP(0);
        }
    }

    public void gameReset() {
        if (menu.isResetValue()) {
            menu.setResetValue(false);
            p1.rest();
            p2.rest();
            p1.setScore(0);
            p2.setScore(0);
            p1Score.setText(String.valueOf(p1.getScore()));
            p2Score.setText(String.valueOf(p2.getScore()));
            ball.rest(Height / 2);
            menu.getChildren().removeAll(menu.getP1status(), menu.getP2status());
            game.setEffect(null);
            mainPane.getChildren().remove(menu);
            clicked = !clicked;
            playerAnimation.play();
            ballAnimation.play();
        }
    }

    public void checkScore() {
        if (p1.getScore() == 7) {
            menu.getP1status().setText("You Win");
            menu.getP1status().setFont(fnt);
            menu.getP1status().setStroke(Color.BLUE);
            menu.getP2status().setText("You Lose");
            menu.getP2status().setFont(fnt);
            menu.getP2status().setStroke(Color.RED);
            try {
                menu.getChildren().addAll(menu.getP1status(), menu.getP2status());
                mainPane.getChildren().add(menu);
            } catch (Exception ignored) {
            }
            playerAnimation.pause();
            ballAnimation.pause();
        } else if (p2.getScore() == 7) {
            menu.getP1status().setText("You Lose");
            menu.getP1status().setFont(fnt);
            menu.getP1status().setStroke(Color.RED);
            menu.getP2status().setText("You Win");
            menu.getP2status().setFont(fnt);
            menu.getP2status().setStroke(Color.BLUE);
            try {
                menu.getChildren().addAll(menu.getP1status(), menu.getP2status());
                mainPane.getChildren().add(menu);
            } catch (Exception ignored) {

            }
            playerAnimation.pause();
            ballAnimation.pause();
        } else {
            menu.getChildren().removeAll(menu.getP1status(), menu.getP2status());
        }
    }

    public void closeCheck(Stage stage) {
        if (menu.isClose()) {
            stage.close();
        }
    }


    public void miniCheck() {
        if (menu.isMinimize()) {
            mainPane.getChildren().remove(menu);
            game.setEffect(null);
            menu.setMini(false);
            playerAnimation.play();
            ballAnimation.play();
            if (menu.isMiniClicked()) {
                clicked = !clicked;
            }

        }
    }

    public static void main(String[] args) {
        launch();
    }
}