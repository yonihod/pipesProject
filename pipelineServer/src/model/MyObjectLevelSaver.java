package model;

import java.io.*;
public class MyObjectLevelSaver implements LevelSaver {

	@Override
	public boolean SaveLevel(PipeGameBoard board, String filePath) {
		try {
			FileOutputStream stream = null;

			stream = new FileOutputStream(filePath);
			ObjectOutputStream e = new ObjectOutputStream(stream);
			e.writeObject(board);
			e.close();
			return true;

		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			return false;
		}
	}

}
