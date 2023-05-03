package com.example.air_hockey_with_java;

import javafx.scene.effect.Bloom;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;

public class GameFrame extends Pane {
    /* constructor contains 2 private methods (gline , garc ) that makes 4 lines of frame of the game
     * and 2 arcs of 18 area
     * 1 objects from 1 classes (line) to make center line because we want a different colour  */
    GameFrame() {
        //Background Color
        super.setStyle("-fx-background-color:black");

        //center line
        Line line = new Line(0, (double) Height / 2, Width, (double) Height / 2);
        line.setStyle("-fx-stroke: #EDE4D9;" + "-fx-stroke-width: 2;");


        //center circle
       /* Circle c = new Circle();
        c.setCenterX((double) Width / 2);
        c.setCenterY((double) Height / 2);
        c.setRadius(50);
        c.setStyle("-fx-stroke: #EDE4D9;" + "-fx-stroke-width: 2;");*/

        //this.getChildren().add(c);
        super.getChildren().add(line);
        //Top Border
        super.getChildren().add(gline(0, 0, Width, 0));
        //Left Border
        super.getChildren().add(gline(0, 0, 0, Height));
        //Right Border
        super.getChildren().add(gline(Width, Height, Width, 0));
        //Bottom Border
        super.getChildren().add(gline(Width, Height, 0, Height));
        //Top Arc
        //a1: start angle , a2: Abs of angle that you will make
        super.getChildren().add(gArc(Width / 2, -1, 50, 52, 180, 180));
        // 2 arc to make centre circle
        super.getChildren().add(gArc(Width / 2, Height/2, 50, 50, 0, 180));
        super.getChildren().add(gArc(Width / 2, Height/2, 50, 50, 180, 180));
        //Bottom Arc
        super.getChildren().add(gArc(Width / 2, Height + 1, 50, 52, 0, 180));

    }


    // method that receive 6 vars and return arc.
    // when you want to return a shape , it depends on the name of class (Arc , line ,.. )
    private Arc gArc(int x, int y, int xR, int yR, int a1, int a2) {
        Arc arc = new Arc(x, y, xR, yR, a1, a2);
        arc.setStyle("-fx-stroke: #EDE4D9;" + "-fx-stroke-width: 2;");
        arc.setType(ArcType.ROUND);

        return arc;
    }
    // method that receive 4 vars and return line.
    private Line gline(int sX, int sY, int eX, int eY) {
        Line line = new Line(sX, sY, eX, eY);
        // Gradient colors in lines
        LinearGradient linearGradient = new LinearGradient(sX, sY, eX, eY, false, CycleMethod.REFLECT,
                new Stop(0, Color.rgb(62, 170, 191, 1)),
                new Stop(0.25, Color.rgb(146, 45, 230, 1)),
                new Stop(0.5, Color.rgb(57, 215, 111, 1)),
                new Stop(.75, Color.rgb(235, 41, 99, 1)),
                new Stop(1, Color.rgb(219, 18, 18, 1)));
        line.setStroke(linearGradient);
        line.setStrokeWidth(10);
        // effect that make colours more beautiful
        Bloom bloom = new Bloom();
        bloom.setThreshold(.9);
        line.setEffect(bloom);
        return line;
    }


}