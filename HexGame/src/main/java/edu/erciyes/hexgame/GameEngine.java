package edu.erciyes.hexgame;

import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class GameEngine {
    int size;
    Color[][] board;
    boolean isPlayer1Turn;
    Player player1;
    Player player2;
    int moveCounter = 1;

    boolean swapOption = true;
    public int firstMoveRow, firstMoveCol;

    public GameEngine(int size) {
        this.size = size;
        this.board = new Color[size][size];
        this.isPlayer1Turn = true;

        this.player1 = new Player(Color.BLUE);
        this.player2 = new Player(Color.RED);
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = null;
            }
        }
    }

    public boolean placePiece(int row, int col) {
        if (board[row][col] != null) {
            return moveCounter == 2;

        }

        else {
            board[row][col] = isPlayer1Turn ? player1.getColor() : player2.getColor();

            moveCounter++;
            isPlayer1Turn = !isPlayer1Turn;
            return true;
        }
    }

    //ilk hamlede 2.oyuncunun swap yapabilmesini sağlar
    public Polygon swapedHex = new Polygon();
    public void swapPieces(){
        if(swapOption)
        {
            initializeBoard();
            board[firstMoveRow][firstMoveCol] = player2.getColor();
            swapedHex.toFront();
            swapedHex.setFill(player2.getColor());

            swapOption = false;
            isPlayer1Turn = true;
            moveCounter++;
        }
    }

    public boolean checkWin(Color playerColor) {
        boolean[][] visited = new boolean[size][size];
        if (playerColor.equals(player1.getColor())) {
            for (int i = 0; i < size; i++) {
                if (board[0][i] == player1.getColor() && dfs(0, i, player1.getColor(), visited)) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (board[i][0] == player2.getColor() && dfs(i, 0, player2.getColor(), visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int row, int col, Color playerColor, boolean[][] visited) {
        if ((playerColor.equals(player1.getColor()) && row == size - 1) ||
                (playerColor.equals(player2.getColor()) && col == size - 1)) {
            return true;
        }

        int[][] directions = {{-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}};
        visited[row][col] = true;

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (isValid(newRow, newCol, playerColor, visited)) {
                if (dfs(newRow, newCol, playerColor, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(int row, int col, Color playerColor, boolean[][] visited) {
        return row >= 0 && row < size && col >= 0 && col < size && board[row][col] == playerColor && !visited[row][col];
    }

    public Color getCurrentPlayerColor() {
        return isPlayer1Turn ? player1.getColor() : player2.getColor();
    }
}