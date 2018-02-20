package model;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MyXMLLevelLoader implements LevelLoader {

	@Override
	public PipeGameBoard loadLevel(String path) {
		FileInputStream stream;
		try {
			stream = new FileInputStream(path);
			XMLDecoder d = new XMLDecoder(stream);
			Object o = d.readObject();
			d.close();
			return (PipeGameBoard) o;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	
	}

}
