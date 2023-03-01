package com.example.a15squares;

/*
 *
 *the GridController class contains method that control the actions of the game
 * such as changing the values of buttons, checking whether a move is legal,
 * and checking whether the game has been won.
 *
 * @Magnus Graham
 * Feb 2023
 */
public class GridController {
    private int[][] buttons;
    //button array

    public GridController() {
        buttons = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = i * 4 + j + 1;
            }
        }
        buttons[3][3] = 0;
    }
    //ctor for gridcontroller
    public int getSquare(int row, int col) {
        return buttons[row][col];
    }
    //returns the coordinates of the square

    public void shift(int startRow, int startCol, int endRow, int endCol) {
        int tempValue = buttons[startRow][startCol];
        buttons[startRow][startCol] = buttons[endRow][endCol];
        buttons[endRow][endCol] = tempValue;
    }
    // change position of button

    public boolean checkEmptySpace(int row, int col) {
        int emptySpaceRow = getEmptyRow();
        int emptySpaceCol = getEmptyCol();
        if (row == emptySpaceRow && col != emptySpaceCol && Math.abs(col - emptySpaceCol) == 1) {
            return true;
        } else if (col == emptySpaceCol && row != emptySpaceRow && Math.abs(row - emptySpaceRow) == 1) {
            return true;
        } else {
            return false;
        }
    }
    //check if there is an empty space adjacent
    public boolean solved() {
        int correctVal = 1;
        for (int[] row : buttons) {
            for (int cell : row) {
                if (cell != correctVal && !(cell == 0 && row == buttons[3] && cell == buttons[3][3])) {
                    return false;
                }
                correctVal++;
            }// iterate through cells, check if they count up 1-15
        }
        return true;
    }
    // checks if puzzle is solved correctly

    public int getEmptyRow() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (getSquare(row, col) == 0) {
                    return row;
                }
            }
        }
        return -1;
    }
    //get row of empty square
    public int getEmptyCol() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (getSquare(row, col) == 0) {
                    return col;
                }
            }
        }
        return -1;
    }
    // getter method for column with an empty space
}