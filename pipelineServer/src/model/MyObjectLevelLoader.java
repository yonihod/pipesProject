package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class MyObjectLevelLoader implements LevelLoader {

	@Override
	public PipeGameBoard loadLevel(String path) {
		try {
			FileInputStream stream = new FileInputStream(path);
			ObjectInputStream ois = null;

			ois = new ObjectInputStream(stream);
			Object o = ois.readObject();
			ois.close();
			return (PipeGameBoard) o;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		
	}

}
