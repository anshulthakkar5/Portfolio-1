package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void appHasAGreeting() {
        App app = new App();
        assertNotNull(app.getGreeting());
    }

    @Test
    void greetingIsCorrect() {
        App app = new App();
        assertEquals("Welcome to Tic-Tac-Toe!", app.getGreeting());
    }

    @Test
    void testPlaceMove() {
        Board board = new Board();
        assertTrue(board.place(1, 'X'));
        assertFalse(board.place(1, 'O'));
    }

    @Test
    void testWinnerRow() {
        Board board = new Board();
        board.place(1, 'X');
        board.place(2, 'X');
        board.place(3, 'X');
        assertTrue(board.isWinner('X'));
    }

    @Test
    void testDraw() {
        Board board = new Board();
        char[] moves = {'X','O','X','X','O','O','O','X','X'};
        for (int i = 0; i < 9; i++) {
            board.place(i + 1, moves[i]);
        }
        assertTrue(board.isDraw());
    }

    @Test
    void testInvalidMove() {
        Board board = new Board();
        assertFalse(board.place(0, 'X'));
        assertFalse(board.place(10, 'X'));
    }
}
