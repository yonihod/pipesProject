package model;

public class PipeGameBoard implements Board<Character> {
	char[][] board;
	
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
