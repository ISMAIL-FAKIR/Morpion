package application.data;

import java.io.*;

public class DataManager {

    public static int rows = 3;
    public static int columns = 3;
    public static int winningCombo = 3;

    public static String gameMode;
    public static GameBoard gameBoard;
    public static Player playerOne;
    public static Player playerTwo;

    public static void setGameMode(String gameMode) {
        DataManager.gameMode = gameMode;
    }

    public static void setPlayerOne(Player playerOne) {
        DataManager.playerOne = playerOne;
    }

    public static void setPlayerTwo(Player playerTwo) {
        DataManager.playerTwo = playerTwo;
    }

    public static void setRows(int rows) {
        DataManager.rows = rows;
    }

    public static void setColumns(int columns) {
        DataManager.columns = columns;
    }

    public static void setWinningCombo(int winningCombo) {
        DataManager.winningCombo = winningCombo;
    }

    public static void setGameBoard(GameBoard gameBoard) {
        DataManager.gameBoard = gameBoard;
    }



  
}