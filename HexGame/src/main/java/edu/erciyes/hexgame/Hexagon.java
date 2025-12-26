package edu.erciyes.hexgame;


import javafx.animation.FillTransition;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;



public class Hexagon {
    int size;
    GameEngine gameEngine;
    Label txWinner=new Label();
    Pane hexagonsLayer = new Pane();
    Pane winnerLayer = new Pane();

    HBox swapOption = new HBox(10);
    Button swapButton = new Button("SWAP");

    public Hexagon(int size) {
        this.size = size;
        this.gameEngine = new GameEngine(size);
    }

    public Pane drawArea(double x, double y, double R) {
        Pane root = new Pane();
        //winner layer effect
        winnerLayer.setVisible(false);
        Effect effect = new DropShadow(20, Color.GRAY);
        winnerLayer.setEffect(effect);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), winnerLayer);
        scaleTransition.setFromX(0);
        scaleTransition.setToX(1);
        scaleTransition.setFromY(0);
        scaleTransition.setToY(1);

        winnerLayer.visibleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                scaleTransition.play();
            }
        });

        for (int i = 0; i < size; i++) {
            double startX = x;
            for (int j = 0; j < size; j++) {
                Polygon hexagon = drawHexagon(i, j, x, y, R);
                hexagon.setOnMouseClicked(hexagonClickHandler(i, j));
                hexagon.setOnMouseEntered(hexagonMouseEnterHandler(hexagon));
                hexagon.setOnMouseExited(hexagonMouseExitHandler(hexagon));
                hexagonsLayer.getChildren().add(hexagon);
                x += R * Math.sqrt(3);
            }
            y += R * 1.5;
            x = startX + R / 2 * Math.sqrt(3);
        }


        txWinner.setStyle("-fx-font-size: 40px; ");
        txWinner.setTextAlignment(TextAlignment.CENTER);
        winnerLayer.getChildren().add(txWinner);
        winnerLayer.setLayoutX(x);
        winnerLayer.setLayoutY(y+R/2);

        swapOption.setVisible(false);
        swapButton.setOnAction(event -> {gameEngine.swapPieces();
            swapOption.setVisible(false);

        });
        swapOption.getChildren().add(swapButton);
        swapOption.toBack();
        swapOption.setLayoutX(700);
        swapOption.setLayoutY(700);
        root.getChildren().addAll(hexagonsLayer, winnerLayer, swapOption);
        return root;
    }


    private EventHandler<MouseEvent> hexagonMouseEnterHandler(Polygon hexagon) {
        return event -> {
            hexagon.toFront();
            ScaleTransition st = new ScaleTransition(Duration.millis(100), hexagon);
            st.setToX(1.1);
            st.setToY(1.1);
            st.play();
        };
    }

    private EventHandler<MouseEvent> hexagonMouseExitHandler(Polygon hexagon) {
        return event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(100), hexagon);
            st.setToX(1.0);
            st.setToY(1.0);
            st.setOnFinished(e -> hexagon.toBack());
            st.play();
        };
    }
    private EventHandler<MouseEvent> hexagonClickHandler(int row, int col) {
        return event -> {

            Polygon hexagon = (Polygon) event.getSource();

            if (gameEngine.board[row][col] != null) {
                return;
            }

            Color currentColor = gameEngine.getCurrentPlayerColor();

            FillTransition fillTransition = new FillTransition(Duration.seconds(0.5), hexagon);
            fillTransition.setFromValue((Color) hexagon.getFill());
            fillTransition.setToValue(currentColor);
            fillTransition.play();
            if (gameEngine.placePiece(row, col)) {
                gameEngine.firstMoveRow = row;
                gameEngine.firstMoveCol = col;

                if (gameEngine.swapOption && gameEngine.isPlayer1Turn == false && gameEngine.moveCounter == 2){
                    swapOption.setVisible(true);
                    swapOption.toFront();
                    gameEngine.swapedHex = hexagon;
                    hexagonsLayer.getChildren().add(gameEngine.swapedHex);
                }

                else if(gameEngine.moveCounter != 2){
                    swapOption.setVisible(false);
                    swapOption.toBack();}


                if (gameEngine.checkWin(currentColor)) {
                    if (currentColor == gameEngine.player1.color) {
                        txWinner.setText("Winner Player 1!");
                        txWinner.setTextFill(currentColor);
                        winnerLayer.setVisible(true);

                    } else {
                        txWinner.setText("Winner Player 2!");
                        txWinner.setTextFill(currentColor);
                        winnerLayer.setVisible(true);

                    }
                    winnerLayer.toFront();
                    disableAllHexagons();
                }

            }
        };
    }
    private void disableAllHexagons() {
        for (Node node : hexagonsLayer.getChildren()) {
            if (node instanceof Polygon) {
                Polygon hexagon = (Polygon) node;
                hexagon.setDisable(true);
            }
        }
    }

    public Polygon drawHexagon(int row, int col, double x, double y, double R) {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
                x, y+(R/2),
                x + R / 2 * Math.sqrt(3), y - R / 2+(R/2),
                x + R  * Math.sqrt(3), y+(R/2),
                x+ R  * Math.sqrt(3), y + R+(R/2),
                x + R / 2 * Math.sqrt(3), y + ( 3*R) / 2+(R/2),
                x , y + R +(R/2)
        );


        polygon.setFill(Color.GRAY);
        polygon.setStrokeWidth(1);

        polygon.setStroke(Color.BLACK);

        return polygon;

    }

}