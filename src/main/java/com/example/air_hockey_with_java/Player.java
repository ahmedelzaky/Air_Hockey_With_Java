package com.example.air_hockey_with_java;

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

import java.util.Map;
import java.util.TreeMap;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;

public class Player extends Circle {
    private final int id;
    private double Velocity;
    private int score = 0;
    private final Timeline bloomAnimation;
    private final Bloom bloom;
    private final GameFrame gameFrame = new GameFrame();
    private final Map<KeyCode, Boolean> keys = new TreeMap<>();


    private double Xoffset = 0;
    private double Yoffset = 0;

    private final double y;
    private final double x;

    Player(double radius, int id, int Y, int R, int G, int B) {
        super(radius);
        this.id = id;
        this.x = ((double) Width / 2);
        this.setCenterX((double) Width / 2);
        this.setCenterY(Y);
        this.y = this.getCenterY();

        if (id == 1) {
            keys.putAll(Map.of(KeyCode.UP, false, KeyCode.DOWN, false, KeyCode.LEFT, false, KeyCode.RIGHT, false, KeyCode.SHIFT, false));
        } else if (id == 2) {
            keys.putAll(Map.of(KeyCode.W, false, KeyCode.S, false, KeyCode.A, false, KeyCode.D, false, KeyCode.CONTROL, false));
        }

        RadialGradient gradientFill = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.rgb(0, 0, 0, 1)), new Stop(0.25, Color.rgb(0, 0, 0, 1)), new Stop(0.3, Color.rgb(0, 0, 0, 1)), new Stop(0.75, Color.rgb(R, G, B, 1)), new Stop(1, Color.rgb(R, G, B, .5)));

        bloom = new Bloom();
        bloom.setThreshold(.6);
        this.setEffect(bloom);
        this.setFill(gradientFill);
        bloomAnimation = new Timeline(new KeyFrame(Duration.millis(200)));
        bloomAnimation.setOnFinished(event -> {
            this.setEffect(this.bloom);
        });
    }


    public void hit() {
        Bloom bloom = new Bloom();
        bloom.setThreshold(0.1);
        this.setEffect(bloom);
        bloomAnimation.play();
    }


    public void keyReleased(KeyEvent event) {
        keys.put(event.getCode(), false);
        if (!event.isShiftDown()) {
            keys.put(KeyCode.SHIFT, false);
        }
        if (!event.isControlDown()) {
            keys.put(KeyCode.CONTROL, false);
        }
    }


    public void keyPressed(KeyEvent event) {
        if (event.isShiftDown()) {
            keys.put(KeyCode.SHIFT, true);
        }
        keys.put(event.getCode(), true);

        if (event.isControlDown()) {
            keys.put(KeyCode.CONTROL, true);
        }
        keys.put(event.getCode(), true);

    }

    public void calculateOffsets() {
        int step = 5;
        if (id == 1) {
            if (keys.get(KeyCode.SHIFT)) {
                step = (int) (step * 1.5);
            }
            if (keys.get(KeyCode.UP) && keys.get(KeyCode.DOWN)) {
                this.Yoffset = 0;
            } else if (keys.get(KeyCode.UP)) {
                this.Yoffset = -step;
            } else if (keys.get(KeyCode.DOWN)) {
                this.Yoffset = step;
            } else {
                this.Yoffset = 0;
            }
            if (keys.get(KeyCode.LEFT) && keys.get(KeyCode.RIGHT)) {
                this.Xoffset = 0;
            } else if (keys.get(KeyCode.LEFT)) {
                this.Xoffset = -step;
            } else if (keys.get(KeyCode.RIGHT)) {
                this.Xoffset = step;
            } else {
                this.Xoffset = 0;
            }
        } else {
            if (keys.get(KeyCode.CONTROL)) {
                step = (int) (step * 1.5);
            }
            if (keys.get(KeyCode.W) && keys.get(KeyCode.S)) {
                this.Yoffset = 0;
            } else if (keys.get(KeyCode.W)) {
                this.Yoffset = -step;
            } else if (keys.get(KeyCode.S)) {
                this.Yoffset = step;
            } else {
                this.Yoffset = 0;
            }
            if (keys.get(KeyCode.A) && keys.get(KeyCode.D)) {
                this.Xoffset = 0;
            } else if (keys.get(KeyCode.A)) {
                this.Xoffset = -step;
            } else if (keys.get(KeyCode.D)) {
                this.Xoffset = step;
            } else {
                this.Xoffset = 0;
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
        calculateOffsets();
        Velocity = Math.sqrt(Math.pow(this.Xoffset, 2) + Math.pow(this.Yoffset, 2));
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






