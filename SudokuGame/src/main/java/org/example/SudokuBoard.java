package org.example;

import java.util.Random;

public class SudokuBoard {
    // Tablica 9x9 reprezentująca planszę Sudoku
    private final int[][] board = new int[9][9];

    // Konstruktor
    public SudokuBoard() {
        fillBoard();
    }

    // Metoda do pobierania wartości z komórki
    public int getCell(int row, int col) {
        return this.board[row][col];
    }

    // Metoda do ustawiania wartości w komórce
    private void setCell(int row, int col, int num) {
        this.board[row][col] = num;
    }

    // Główna metoda wypełniająca planszę
    public void fillBoard() {
        boolean solved;
        do {
            // Wyczyść planszę
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    setCell(i, j, 0);
                }
            }
            // Losowe liczby startowe
            randomize();
            // Próbuj rozwiązać planszę za pomocą algorytmu backtracking
            solved = solve(0, 0);
        } while (!solved);  // Powtarzaj aż uzyskasz poprawną planszę
    }

    // Sprawdzanie, czy można umieścić daną liczbę w komórce
    private boolean isValid(int row, int col, int num) {
        // Sprawdzanie wiersza i kolumny
        for (int i = 0; i < 9; i++) {
            if (num == getCell(i, col) || num == getCell(row, i)) {
                return false;
            }
        }

        // Sprawdzanie bloku 3x3
        for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++) {
            for (int j = col / 3 * 3; j < col / 3 * 3 + 3; j++) {
                if (num == getCell(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Algorytm backtracking do rozwiązywania Sudoku
    private boolean solve(int row, int col) {
        if (row == 9) {  // Jeżeli przejdziemy przez wszystkie wiersze, Sudoku jest rozwiązane
            return true;
        } else if (col == 9) {  // Jeżeli przejdziemy przez wszystkie kolumny, przejdź do następnego wiersza
            return solve(row + 1, 0);
        } else if (getCell(row, col) != 0) {  // Jeżeli komórka nie jest pusta, przejdź dalej
            return solve(row, col + 1);
        } else {
            // Próbujemy każdej liczby od 1 do 9
            for (int num = 1; num <= 9; num++) {
                if (isValid(row, col, num)) {
                    setCell(row, col, num);
                    if (solve(row, col + 1)) {
                        return true;  // Jeżeli Sudoku zostało rozwiązane, zwracamy true
                    }
                    setCell(row, col, 0);  // Cofanie (backtracking)
                }
            }
            return false;
        }
    }

    // Losowe wypełnienie planszy kilkoma liczbami startowymi
    private void randomize() {
        Random random = new Random();
        int row;
        int col;
        int num;
        int nums = 0;

        while (nums < 17) {  //17 liczb - minimalną liczbą dla rozwiązywalnej planszy)
            row = random.nextInt(9);  // losowanie zakresu 0-8 dla wierszy
            col = random.nextInt(9);  // Poprawne losowanie zakresu 0-8 dla kolumn

            num = random.nextInt(9) + 1;  // Losowanie liczby w zakresie 1-9

            if (isValid(row, col, num) && getCell(row, col) == 0) {
                setCell(row, col, num);
                nums++;
            }
        }
    }

    // Metoda zwracająca kopię planszy (aby uniknąć bezpośredniego dostępu do prywatnych pól)
    public int[][] getBoard() {
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, 9);
        }
        return copy;
    }
}
