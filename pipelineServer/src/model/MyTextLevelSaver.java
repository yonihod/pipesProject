package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MyTextLevelSaver implements LevelSaver {

	@Override
	public boolean SaveLevel(Level level, String filePath) {
		String fileContent = "";
		ArrayList<ArrayList<GameObject>> board = level.getGameBoard();

		LevelConvertor lc = new LevelConvertor();

		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.get(i).size(); j++) {
				fileContent += lc.convertFromObjectToChar(board.get(i).get(j));
			}

			fileContent += System.lineSeparator();
		}

		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stream.write(fileContent.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
}
