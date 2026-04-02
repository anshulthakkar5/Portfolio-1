package org.example;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");

        boolean playAgain = true;
        while (playAgain) {
            TicTacToeGame game = new TicTacToeGame(scanner);
            game.play();

            playAgain = promptPlayAgain(scanner);
        }

        System.out.println("Goodbye!");
    }

    // For JUnit testing
    public String getGreeting() {
        return "Welcome to Tic-Tac-Toe!";
    }

    private static boolean promptPlayAgain(Scanner scanner) {
        while (true) {
            System.out.print("\nWould you like to play again (yes/no)? ");
            String line = scanner.nextLine();

            if (line == null) {
                System.out.println("That is not a valid entry!");
                continue;
            }

            String s = line.trim().toLowerCase();
            if (s.equals("yes")) return true;
            if (s.equals("no")) return false;

            System.out.println("That is not a valid entry!");
        }
    }
}

class TicTacToeGame {
    private final Scanner scanner;
    private final Board board;
    private char currentPlayer;

    TicTacToeGame(Scanner scanner) {
        this.scanner = scanner;
        this.board = new Board();
        this.currentPlayer = 'X';
    }

    void play() {
        System.out.println();
        board.print();

        while (true) {

            System.out.println("\nPlayer " + currentPlayer + "'s turn");

            Integer move = promptMove();
            if (move == null) {
                System.out.println("\nInvalid input! Try again.\n");
                continue;
            }

            boolean placed = board.place(move, currentPlayer);
            if (!placed) {
                System.out.println("\nThat spot is already taken! Try again.\n");
                continue;
            }

            System.out.println();
            board.print();

            if (board.isWinner(currentPlayer)) {
                System.out.println("\nPlayer " + currentPlayer + " wins!");
                return;
            }

            if (board.isDraw()) {
                System.out.println("\nIt's a draw!");
                return;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private Integer promptMove() {
        System.out.print("\nWhat is your move? ");
        String line = scanner.nextLine();

        if (line == null) return null;

        String s = line.trim();
        if (s.isEmpty()) return null;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!(ch >= '0' && ch <= '9')) return null;
        }

        try {
            int val = Integer.parseInt(s);
            if (val < 1 || val > 9) return null;
            return val;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

class Board {
    private final char[] cells;

    Board() {
        cells = new char[9];
    }

    boolean place(int position1to9, char player) {
        if (position1to9 < 1 || position1to9 > 9) return false;
        int idx = position1to9 - 1;
        if (cells[idx] != '\0') return false;

        cells[idx] = player;
        return true;
    }

    boolean isWinner(char player) {
        int[][] lines = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };

        for (int[] line : lines) {
            if (cells[line[0]] == player &&
                cells[line[1]] == player &&
                cells[line[2]] == player) {
                return true;
            }
        }
        return false;
    }

    boolean isDraw() {
        for (char c : cells) {
            if (c == '\0') return false;
        }
        return true;
    }

    void print() {
        System.out.println(formatRow(0));
        System.out.println("  -----+-----+-----");
        System.out.println(formatRow(3));
        System.out.println("  -----+-----+-----");
        System.out.println(formatRow(6));
    }

    private String formatRow(int startIdx) {
        return "    " + cellDisplay(startIdx) + "  |  "
                + cellDisplay(startIdx + 1) + "  |  "
                + cellDisplay(startIdx + 2);
    }

    private String cellDisplay(int idx) {
        return (cells[idx] == '\0') ? String.valueOf(idx + 1)
                                   : String.valueOf(cells[idx]);
    }
}
