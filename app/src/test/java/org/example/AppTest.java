package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }

    
    @Test
    void appGreetingIsCorrect() {
        App app = new App();
        assertEquals("Welcome to Tic-Tac-Toe!", app.getGreeting());
    }


    @Test
    void testBoardPlacement() {
        Board board = new Board();
        assertTrue(board.place(1, 'X'));
        assertFalse(board.place(1, 'O')); // already taken
    }


    @Test
    void testWinner() {
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
}
