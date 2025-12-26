package edu.erciyes.hexgame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root = new AnchorPane();

        int width = 1400;
        int height = 780;
        stage.setResizable(false);//fullscreen off
        Scene scene = new Scene(root, width, height);
        //Background
        Image backgroundImage = new Image(getClass().getResource("/Back.png").toExternalForm());
        BackgroundImage bgImage = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(bgImage));
        Image fontImage = new Image(getClass().getResource("/font.png").toExternalForm());
        ImageView imageView = new ImageView(fontImage);
        //Logo
        StackPane.setAlignment(imageView, Pos.CENTER);
        StackPane.setMargin(imageView, new Insets(0, 0, 0, 0));
        root.getChildren().add(imageView);
        //bottom panel
        HBox hBox = new HBox(20);
        hBox.setMaxWidth(root.getWidth());
        hBox.setBorder(Border.stroke(Color.BLACK));
        hBox.setMinWidth(root.getWidth());
        hBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        AnchorPane.setLeftAnchor(hBox, 0.0);
        AnchorPane.setRightAnchor(hBox, 0.0);
        AnchorPane.setBottomAnchor(hBox, 0.0);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setMinHeight(50);
        root.getChildren().add(hBox);

        ToggleGroup tg = new ToggleGroup();
        RadioButton rd1 = new RadioButton("5X5");
        rd1.setSelected(true);
        rd1.setToggleGroup(tg);
        RadioButton rd2 = new RadioButton("11X11");
        rd2.setToggleGroup(tg);
        RadioButton rd3 = new RadioButton("17X17");
        rd3.setToggleGroup(tg);

        Button startButton = new Button("START");
        hBox.getChildren().addAll(rd1, rd2, rd3, startButton);
        //Create Hexagones
        HexagonPane hexagonPane = new HexagonPane();
        startButton.setOnAction(event -> {
            imageView.setVisible(false);
            RadioButton selectedRadioButton = (RadioButton) tg.getSelectedToggle();
                if (rd1.isSelected()) {
                    hexagonPane.drawHexagons(5, 1000/(7*Math.sqrt(3)));
                } else if (rd2.isSelected()) {
                    hexagonPane.drawHexagons(11, 1000/(16*Math.sqrt(3)));
                } else if (rd3.isSelected()) {
                    hexagonPane.drawHexagons(17, 1000/(25*Math.sqrt(3)));
                }

        });

        root.getChildren().addAll(hexagonPane);
        stage.setScene(scene);
        stage.setTitle("Hex Game");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
