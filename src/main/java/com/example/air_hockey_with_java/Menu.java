package com.example.air_hockey_with_java;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.example.air_hockey_with_java.Game.Height;
import static com.example.air_hockey_with_java.Game.Width;


public class Menu extends GridPane {
    private boolean mute = false;
    private boolean close = false;
    private boolean mini = true;
    private int iconSize = 30;


    private boolean MiniClicked = false;
    private Font fnt = Font.font("Time New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);

    private boolean resetValue = false;
    private Text p1status = new Text(100, 200, "");
    private Text p2status = new Text(100, 530, "");

    Menu() throws FileNotFoundException {
        // music Button
        Button musicBtn = new Button();
        Image muteImg = new Image(new FileInputStream("images\\mute.png"));
        ImageView muteView = new ImageView(muteImg);
        Image unmuteImg = new Image(new FileInputStream("images\\unmute.png"));
        ImageView unmuteView = new ImageView(unmuteImg);
        muteView.setFitHeight(iconSize);
        muteView.setFitWidth(iconSize);
        unmuteView.setFitHeight(iconSize);
        unmuteView.setFitWidth(iconSize);
        musicBtn.setGraphic(unmuteView);
        musicBtn.setStyle("-fx-background-color: transparent ;");

        // Close  Button
        Button closeBtn = new Button();
        Image closeImg = new Image(new FileInputStream("images\\close64.png"));
        ImageView closeView = new ImageView(closeImg);
        closeView.setFitHeight(iconSize);
        closeView.setFitWidth(iconSize);
        closeBtn.setGraphic(closeView);
        closeBtn.setStyle("-fx-background-color: transparent ;");

        // minimize  Button
        Button miniBtn = new Button();
        Image miniImg = new Image(new FileInputStream("images\\minimize64.png"));
        ImageView miniView = new ImageView(miniImg);
        miniView.setFitHeight(iconSize);
        miniView.setFitWidth(iconSize);
        miniBtn.setGraphic(miniView);
        miniBtn.setStyle("-fx-background-color: transparent ;");

        //Restart Button
        Button resetBtn = new Button("Restart");
        resetBtn.setPrefSize(150, 25);
        resetBtn.setTextFill(Color.WHITE);
        resetBtn.setStyle("-fx-background-color:  transparent;" + "-fx-border-color:  rgb(200, 34, 255 , .6);" + "-fx-border-width:5;" + "-fx-border-radius:50;" + "-fx-cursor: pointer;");
        resetBtn.setFont(fnt);
        double recWidth = Width - 100;
        double recHeight = Height - 550;

        //Create a Rectangle to add button on it
        Rectangle rectangle = new Rectangle(recWidth, recHeight);
        rectangle.setLayoutX((double) Width / 2 - recWidth / 2);
        rectangle.setLayoutY((double) Height / 2 - recHeight / 2);
        rectangle.setStyle("-fx-border-radius:50;");
        rectangle.setFill(Color.rgb(200, 34, 255, .2));
        rectangle.setStroke(Color.rgb(200, 34, 255, .8));
        rectangle.setStrokeWidth(5);

        //add elements to pane
        this.setPrefSize(Width, Height);
        this.setStyle("-fx-background-color:  rgb(0, 0, 0 , .6);");
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(rectangle);

        //make a box for close and minimuize
        HBox controlBox = new HBox(0);
        controlBox.getChildren().addAll(closeBtn, miniBtn);
        this.add(controlBox, 0, 0);

        HBox musicBox = new HBox();
        musicBox.getChildren().add(musicBtn);
        musicBox.setTranslateX(recWidth - 45);
        this.add(musicBox, 0, 0);

        this.add(resetBtn, 0, 0);
        resetBtn.setTranslateX(recWidth / 2 - 75);

        this.p1status.setTranslateY((double) -Height / 2 + 100);
        this.p1status.setTranslateX(50);
        this.p2status.setTranslateY((double) Height / 2 - 100);
        this.p2status.setTranslateX(50);

        //  Media sound = new Media(getClass().getResource("Clown.mp3").toString());
        //  MediaPlayer mediaPlayer = new MediaPlayer(sound);
        //  mediaPlayer.play();

        musicBtn.setOnAction(e -> {
            mute = !mute;
            if (mute) {
                musicBtn.setGraphic(muteView);
                //mediaPlayer.pause();
            } else {
                musicBtn.setGraphic(unmuteView);
                //mediaPlayer.play();
            }
        });

        resetBtn.setOnAction(e -> {
            reset();
        });

        closeBtn.setOnAction(e -> {
            close();
        });
        miniBtn.setOnAction(e -> {
            minimize();
            MiniClicked = true;
        });
    }

    public void reset() {
        resetValue = true;
    }

    public void close() {
        close = true;
    }

    public void minimize() {
        mini = true;
    }

    public boolean isClose() {
        return close;
    }

    public boolean isMinimize() {
        return mini;
    }

    public boolean isMiniClicked() {
        return MiniClicked;
    }

    public void setMini(boolean mini) {
        this.mini = mini;
    }

    public boolean isResetValue() {
        return resetValue;
    }

    public void setP1status(Text p1status) {
        this.p1status = p1status;
    }

    public void setP2status(Text p2status) {
        this.p2status = p2status;
    }

    public Text getP1status() {
        return p1status;
    }

    public Text getP2status() {
        return p2status;
    }

    public void setResetValue(boolean resetValue) {
        this.resetValue = resetValue;
    }
}
