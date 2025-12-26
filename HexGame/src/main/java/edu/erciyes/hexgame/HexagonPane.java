package edu.erciyes.hexgame;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class HexagonPane extends Pane {
    public void drawHexagons(int boardSize, double R) {
        this.getChildren().clear();
        Hexagon hex = new Hexagon(boardSize);
        Pane hexagones = hex.drawArea(200, 5, R);
        this.getChildren().add(hexagones);
    }
}
