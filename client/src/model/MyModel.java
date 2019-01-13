package model;

import view.PipeBoard;

import java.util.Observable;

public class MyModel extends Observable implements Model {
    private char[][] board;

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }
}
