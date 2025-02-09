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
import javafx.scene.layout.HBox;
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
    private boolean clicked = false;

    private final Player p1 = new Player(30, 1, 90, 57, 255, 20);
    private final Player p2 = new Player(30, 2, Height - 90, 15, 240, 252);
    private final Ball ball = new Ball();
    private final Text p1Score = new Text();
    private final Text p2Score = new Text();
    private final Menu menu = new Menu();
    private final StartMenu startMenu = new StartMenu(menu);
    private final GameFrame game = new GameFrame();
    private final GaussianBlur blurEffect = new GaussianBlur();
    private final LoadingScreen loadingScreen = new LoadingScreen();
    private final Font fnt = Font.font("Time New Roman", FontWeight.BOLD, FontPosture.ITALIC, 40);

    private Timeline ballAnimation;
    private Timeline playerAnimation;
    private Pane mainPane;


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
        loadingScreen.animationPlay();


        startMenu.getChildren().add(loadingScreen);
        mainPane = new Pane();
        mainPane.getChildren().add(startMenu);

        Scene scene = new Scene(mainPane, Width, Height);

        // Add the icon to the list of icons for the stage
        Image icon = new Image(new FileInputStream("images\\icon.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Air Hockey Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        ball.setFriction(.99);

        scene.setOnKeyPressed(e -> {
            if (startMenu.isMove()) {
                p1.keyPressed(e);
                p2.keyPressed(e);
            }
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

        int frameTime = 16;
        playerAnimation = new Timeline(new KeyFrame(Duration.millis(frameTime), e -> {
            p1.Move();
            p2.Move();
        }));

        playerAnimation.setCycleCount(Timeline.INDEFINITE);

        ballAnimation = new Timeline(new KeyFrame(Duration.millis(frameTime), e -> checkCollision()));
        ballAnimation.setCycleCount(Timeline.INDEFINITE);

        Timeline loading = new Timeline(new KeyFrame(Duration.millis(frameTime), e -> loadingAnimation()));
        loading.setCycleCount(500);
        loading.play();

        Timeline resetChecker = new Timeline(new KeyFrame(Duration.millis(frameTime), e -> gameReset()));
        resetChecker.setCycleCount(Timeline.INDEFINITE);
        resetChecker.play();

        Timeline gameOver = new Timeline(new KeyFrame(Duration.millis(frameTime), e -> checkScore()));
        gameOver.setCycleCount(Timeline.INDEFINITE);
        gameOver.play();

        Timeline startChecker = new Timeline(new KeyFrame(Duration.millis(frameTime), e -> {
            start();
        }));

        startChecker.setCycleCount(Timeline.INDEFINITE);
        startChecker.play();

        Timeline closeAndMiniChecker = new Timeline(new KeyFrame(Duration.millis(frameTime), e -> {
            closeCheck(stage);
            miniCheck();
        }));

        closeAndMiniChecker.setCycleCount(Timeline.INDEFINITE);
        closeAndMiniChecker.play();

    }

    private void start() {
        if (startMenu.getPlay()) {
            startMenu.setPlay(false);
            mainPane.getChildren().remove(startMenu);
            mainPane.getChildren().add(game);
            HBox musicBox = new HBox();
            musicBox.getChildren().add(menu.getMusicBtn());
            musicBox.setTranslateX(menu.getRecWidth() - 45);
            menu.add(musicBox, 0, 0);
            playerAnimation.play();
            ballAnimation.play();
        }
    }

    public void checkCollision() {
        game.requestFocus();
        //  System.out.println(p1.getScore() + " : " + p2.getScore());
        if (ball.getCenterX() < (double) Width / 2 + game.getArcRadius() - ball.getRadius() && ball.getCenterX() > (double) Width / 2 - game.getArcRadius() + ball.getRadius() && ball.getCenterY() - ball.getRadius() <= 0) {
            try {
                Thread.sleep(500); // sleep for .5 seconds
            } catch (InterruptedException e) {
                System.out.println("Error");
                System.out.println(e.getMessage());
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
        } else if (ball.getCenterX() < (double) Width / 2 + game.getArcRadius() - ball.getRadius() && ball.getCenterX() > (double) Width / 2 - game.getArcRadius() + ball.getRadius() && ball.getCenterY() + ball.getRadius() >= Height) {
            try {
                Thread.sleep(500); // sleep for .5 seconds
            } catch (InterruptedException e) {
                System.out.println("Error");
                System.out.println(e.getMessage());
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

        ball.checkCollision(p1);
        ball.checkCollision(p2);
        // move the ball
        ball.move();
    }


    public void isClicked() {
        if (startMenu.isAddMenu()) {
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
    }

    public void loadingAnimation() {
        if (loadingScreen.finish()) {
            startMenu.getChildren().remove(loadingScreen);
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