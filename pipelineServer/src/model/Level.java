package model;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Level implements Serializable {
	private ArrayList<ArrayList<GameObject>> gameBoard;
	private Integer gameId;

	public Level() {
	}

	public Level(ArrayList<ArrayList<GameObject>> go) {
		this.gameBoard = go;
	}

	Integer getGameId() {
		return gameId;
	}

	void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	ArrayList<ArrayList<GameObject>> getGameBoard() {
		return gameBoard;
	}

	void setGameBoard(ArrayList<ArrayList<GameObject>> gameBoard) {
		this.gameBoard = gameBoard;
	}

	@Override
	public String toString() {
		return gameBoard.stream().map(Object::toString).collect(Collectors.joining());
	}

	@Override
	public int hashCode() {
		gameId = gameBoard.stream().map(Object::toString).collect(Collectors.joining()).hashCode();
		return gameId;
	}
}
