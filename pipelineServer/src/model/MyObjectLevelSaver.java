package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MyObjectLevelSaver implements LevelSaver {

	@Override
	public boolean SaveLevel(Level level, String filePath) {
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(filePath);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ObjectOutputStream e = null;
		try {
			e = new ObjectOutputStream(stream);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			e.writeObject(level);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			e.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}

}
