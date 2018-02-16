package model;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MyXMLLevelSaver implements LevelSaver {

	@Override
	public boolean SaveLevel(Level level, String filePath) {
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(filePath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		XMLEncoder e = new XMLEncoder(stream);
		e.writeObject(level);
		e.close();
		return true;
	}

}
