package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyTextLevelLoader implements LevelLoader {

	@Override
	public PipeGameBoard loadLevel(String path) {


		int c = -1;
		String fileContent = "";

		try {
			FileInputStream stream = new FileInputStream(path);
			while ((c = stream.read()) != -1) {
				char currentChar = (char) c;
				fileContent += currentChar;
			}
			stream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String[] rows = fileContent.split("\n");

		char[][] chars = new char[rows.length][];

		for (int i = 0; i < chars.length; i++) {
			chars[i] = rows[i].toCharArray();
		}

		return new PipeGameBoard(chars);
	}

}
