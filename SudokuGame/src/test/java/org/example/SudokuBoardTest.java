package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    void validationOfFillBoard() {
        SudokuBoard board1 = new SudokuBoard();
        board1.fillBoard();
        boolean valid = true;

        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                for (int k = 0; k < 9; k++) {
                    if (board1.getCell(k, j) == board1.getCell(i, j) && k != i) {
                        valid = false;
                    }
                    if (board1.getCell(i, k) == board1.getCell(i, j) && k != j) {
                        valid = false;
                    }
                }

                for (int k = i / 3 * 3; k < i / 3 * 3 + 3; k++) {
                    for (int l = j / 3 * 3; l < j / 3 * 3 + 3; l++) {
                        if (board1.getCell(k, l) == board1.getCell(i, j) && k != i && l != j) {
                            valid = false;
                        }
                    }
                }
            }
        }
        assertTrue(valid);
    }

    @Test
    void randomizationOfFillBoard() {
        SudokuBoard board1 = new SudokuBoard();
        board1.fillBoard();

        int[][] boardTest1 = new int[9][9];
        int[][] boardTest2 = new int[9][9];

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                boardTest1[i][j] = board1.getCell(i, j);
            }
        }

        board1.fillBoard();

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                boardTest2[i][j] = board1.getCell(i, j);
            }
        }

        assertFalse(Arrays.deepEquals(boardTest1, boardTest2));
    }
}
