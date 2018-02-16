package model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyTextLevelLoader implements LevelLoader {

	@Override
	public Level loadLevel(InputStream stream) {
		ArrayList<ArrayList<GameObject>> go = new ArrayList<ArrayList<GameObject>>();
		go.add(new ArrayList<GameObject>());

		LevelConvertor lc = new LevelConvertor();
		int c = -1;
		int currentLine = 0;
		try {
			while ((c = stream.read()) != -1) {
				char currentChar = (char) c;

				if (currentChar == '\n' || currentChar == '\\') {
					currentLine++;
					go.add(new ArrayList<GameObject>());
				}
				else if(currentChar == 'n'){}
				else {
					go.get(currentLine).add(lc.convertFromCharToObject(currentChar));
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Level level = new Level(go);
		return level;
	}

}
