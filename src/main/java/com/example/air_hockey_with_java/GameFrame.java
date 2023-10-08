package com.example.air_hockey_with_java;

import javafx.scene.effect.Bloom;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;

public class GameFrame extends Pane {
    private static final double strokeWidth = 5;
    private final double arcRadius = ((double) 1 / 5) * Width;

    GameFrame() {
        //Background Color
        this.setStyle("-fx-background-color:black");

        //center line
        Line line = new Line(0, Height / 2, Width, Height / 2);
        line.setStyle("-fx-stroke: #EDE4D9;" + "-fx-stroke-width: 2;");

        //center circle
        Circle c = new Circle();
        c.setCenterX(Width / 2);
        c.setCenterY(Height / 2);
        c.setRadius(50);
        c.setStyle("-fx-stroke: #EDE4D9;" + "-fx-stroke-width: 2;");

        this.getChildren().add(c);
        this.getChildren().add(line);
        //Top Border
        this.getChildren().add(gline(0, 0, Width, 0));
        //Left Border
        this.getChildren().add(gline(0, 0, 0, Height));
        //Right Border
        this.getChildren().add(gline(Width, Height, Width, 0));
        //Bottom Border
        this.getChildren().add(gline(Width, Height, 0, Height));
        //Top Arc
        this.getChildren().add(gArc(0, 180));
        //Bottom Arc
        this.getChildren().add(gArc(Height, 0));

    }

    private Arc gArc(double y, double a1) {
        Arc arc = new Arc(Width / 2, y, arcRadius, arcRadius, a1, 180);
        arc.setStyle("-fx-stroke: #EDE4D9;" + "-fx-stroke-width: 2;");
        return arc;
    }

    private Line gline(double sX, double sY, double eX, double eY) {
        Line line = new Line(sX, sY, eX, eY);
        LinearGradient linearGradient = new LinearGradient(sX, sY, eX, eY, false, CycleMethod.REFLECT, new Stop(0, Color.rgb(62, 170, 191, 1)), new Stop(0.25, Color.rgb(146, 45, 230, 1)), new Stop(0.5, Color.rgb(57, 215, 111, 1)), new Stop(.75, Color.rgb(235, 41, 99, 1)), new Stop(1, Color.rgb(219, 18, 18, 1)));
        line.setStroke(linearGradient);
        line.setStrokeWidth(strokeWidth * 2);
        Bloom bloom = new Bloom();
        bloom.setThreshold(.9);
        line.setEffect(bloom);
        return line;
    }

    public double getArcRadius() {
        return arcRadius;
    }

    static public double getStrokeWidth() {
        return strokeWidth;
    }
}
