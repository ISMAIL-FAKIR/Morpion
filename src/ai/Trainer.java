package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Trainer {

    public static int player = -1;
    public static int ai = 1;
    public static int winner;
    private static double[] gameboard;
    public static List<double[]> states;
    public static List<Integer> nextTile;

    public static void reset() {
        winner = 0;
        states = new ArrayList<>();
        nextTile = new ArrayList<>();
    }

    public static void generateGame() {
        gameboard = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        int current = player;
        int turn = 0;
        boolean hasWinner = false;
        while (!hasWinner && turn < gameboard.length) {
            // Each loop is one turn
            turn++;
            int tile = chooseRandomTile(current);
            if (current == player) {
                double[] copy = gameboard.clone();
                states.add(copy);
            }
            else {
                nextTile.add(tile);
            }

            hasWinner = checkWinner();
            if (!hasWinner) {
                current = switchPlayerAI(current);
            }
        }

        if (hasWinner) {
            winner = current;
        }

        // TESTING
        // print();
    }

    private static void print() {
        for (int i = 0; i < states.size(); i++) {
            System.out.println("gameboard " + i + ":");
            double[] gb = states.get(i);
            System.out.println(gb[0] + "\t" + gb[1] + "\t" + gb[2]);
            System.out.println(gb[3] + "\t" + gb[4] + "\t" + gb[5]);
            System.out.println(gb[6] + "\t" + gb[7] + "\t" + gb[8]);
            System.out.print("Next AI tile: ");
            try {
                System.out.println(nextTile.get(i));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("None");
            }
            System.out.println("--------------------");
        }
        System.out.println("Winner: " + winner);
    }

    private static int chooseRandomTile(int player) {
        int rand;
        do {
            rand = ThreadLocalRandom.current().nextInt(0, gameboard.length);
        } while (gameboard[rand] != 0);

        gameboard[rand] = player;

        return rand;
    }

    private static boolean checkWinner() {
        // Lines
        for (int i = 0; i < 9; i+=3) {
            if (gameboard[i] != 0 && gameboard[1+i] == gameboard[i] && gameboard[2+i] == gameboard[i]) {
//                System.out.println("Won at: " + i + " " + (i+1) + " " + (i+2));
                return true;
            }
        }

        // Columns
        for (int i = 0; i < 3; i++) {
            if (gameboard[i] != 0 && gameboard[3+i] == gameboard[i] && gameboard[6+i] == gameboard[i]) {
//                System.out.println("Won at: " + i + " " + (3+i) + " " + (6+i));
                return true;
            }
        }

        // Diagonals
        if (gameboard[0] != 0 && gameboard[4] == gameboard[0] && gameboard[8] == gameboard[0]) {
//            System.out.println("Won at: 0 4 8");
            return true;
        }
        if (gameboard[2] != 0 && gameboard[4] == gameboard[2] && gameboard[6] == gameboard[2]) {
//            System.out.println("Won at: 2 4 6");
            return true;
        }

        return false;
    }

    private static int switchPlayerAI(int current) {
        if (current == player) {
            return ai;
        }
        else {
            return player;
        }
    }
}
