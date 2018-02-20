package model;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MyXMLLevelSaver implements LevelSaver {

	@Override
	public boolean SaveLevel(PipeGameBoard board, String filePath) {
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(filePath);
			XMLEncoder e = new XMLEncoder(stream);
			e.writeObject(board);
			e.close();
			return true;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return false;
		}
		
	}

}
