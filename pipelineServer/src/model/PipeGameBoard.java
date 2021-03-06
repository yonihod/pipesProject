package model;

import java.io.Serializable;

public class PipeGameBoard implements Board<Character>, Serializable {
	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	char[][] board;
	
	public PipeGameBoard() {}
	
	public PipeGameBoard(char[][] board) {
		this.board = board;
	}

	@Override
	public Character getXY(Integer x, Integer y) {
		return this.board[y][x];
	}

	@Override
	public Integer getHieght() {
		return this.board.length;
	}

	@Override
	public Integer getWidth() {
		// TODO Auto-generated method stub
		return this.board[0].length;
	}

}
