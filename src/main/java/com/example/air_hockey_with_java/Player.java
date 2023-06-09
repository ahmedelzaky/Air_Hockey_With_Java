package com.example.air_hockey_with_java;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.Bloom;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;

public class Player extends Circle {
    private final int id;
    private double Velocity;
    private int score = 0;
    private Timeline bloomAnimation;
    private Bloom bloom;
    private GameFrame gameFrame = new GameFrame();

    private double Xoffset = 0;
    private double Yoffset = 0;

    private double y;
    private double x;

    Player(double radius, int id, int Y, int R, int G, int B) {
        super(radius);
        this.id = id;
        this.x = ((double) Width / 2);
        this.setCenterX((double) Width / 2);
        this.setCenterY(Y);
        this.y = this.getCenterY();

        RadialGradient gradientFill = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.rgb(0, 0, 0, 1)), new Stop(0.25, Color.rgb(0, 0, 0, 1)), new Stop(0.3, Color.rgb(0, 0, 0, 1)), new Stop(0.75, Color.rgb(R, G, B, 1)), new Stop(1, Color.rgb(R, G, B, .5)));

        bloom = new Bloom();
        bloom.setThreshold(.6);
        this.setEffect(bloom);
        this.setFill(gradientFill);

        Timeline hitAnimationChecker = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            try {
                if (bloomAnimation.getStatus() != Animation.Status.RUNNING) {
                    this.setEffect(bloom);
                }
            } catch (Exception ignored) {
            }
        }));
        hitAnimationChecker.setCycleCount(Timeline.INDEFINITE);
        hitAnimationChecker.play();
    }


    public void hit() {
        Bloom bloom = new Bloom();
        bloom.setThreshold(0.1);
        this.setEffect(bloom);
        bloomAnimation = new Timeline(new KeyFrame(Duration.millis(200)));
        bloomAnimation.play();
    }


    public void keyReleased(KeyEvent event) {


        if (id == 1) {
            if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
                this.Yoffset = 0;
            } else if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
                this.Xoffset = 0;
            }
        } else if (id == 2) {
            if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.S) {
                this.Yoffset = 0;
            } else if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.D) {
                this.Xoffset = 0;
            }
        }
    }


    public void keyPressed(KeyEvent event) {
        int step = 2;
        Velocity = 1.5 * step;

        if (id == 1) {
            if (event.isShiftDown()) {
                step = 3;
                Velocity = 1.5 * step;
            }
            switch (event.getCode()) {
                case UP:
                    this.Yoffset = -step;
                    break;
                case DOWN:
                    this.Yoffset = step;

                    break;
                case LEFT:
                    this.Xoffset = -step;
                    break;
                case RIGHT:
                    this.Xoffset = step;
                    break;
                default:
                    break;
            }

        } else if (id == 2) {

            if (event.isControlDown()) {
                step = 3;
                Velocity = 1.5 * step;
            }
            switch (event.getCode()) {
                case W:
                    this.Yoffset = -step;
                    break;
                case S:
                    this.Yoffset = step;

                    break;
                case A:
                    this.Xoffset = -step;
                    break;
                case D:
                    this.Xoffset = step;
                    break;
                default:
                    break;
            }
        }
    }


    public void rest() {
        this.setCenterY(y);
        this.setCenterX(x);
        FadeTransition playerFad = new FadeTransition(Duration.millis(400), this);
        playerFad.setFromValue(.3);
        playerFad.setToValue(1);
        playerFad.setCycleCount(3);
        playerFad.play();
    }


    public void Move() {
        if (id == 1) {
            if (this.getCenterX() + this.Xoffset < (Width - this.getRadius() - gameFrame.getStrokeWidth()) && this.getCenterX() + this.Xoffset > this.getRadius() + gameFrame.getStrokeWidth())
                this.setCenterX(this.getCenterX() + this.Xoffset);
            if (this.getCenterY() + this.Yoffset > this.getRadius() + gameFrame.getStrokeWidth() && this.getCenterY() + this.Yoffset + this.getRadius() - gameFrame.getStrokeWidth() < (double) Height / 2)
                this.setCenterY(this.getCenterY() + this.Yoffset);

        } else if (id == 2) {
            if (this.getCenterX() + this.Xoffset < (Width - this.getRadius() - gameFrame.getStrokeWidth()) && this.getCenterX() + this.Xoffset > this.getRadius() + gameFrame.getStrokeWidth())
                this.setCenterX(this.getCenterX() + this.Xoffset);
            if (this.getCenterY() + this.Yoffset - this.getRadius() > ((double) Height / 2) - gameFrame.getStrokeWidth() && this.getCenterY() + this.Yoffset < Height - this.getRadius() - gameFrame.getStrokeWidth())
                this.setCenterY(this.getCenterY() + this.Yoffset);
        }
    }

    public void addPoint() {
        ++score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getVelocity() {
        return Velocity;
    }
}






