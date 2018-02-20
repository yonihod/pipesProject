package model;

import java.io.*;

public class MyTextLevelSaver implements LevelSaver {

	@Override
	public boolean SaveLevel(PipeGameBoard baord, String filePath) {
		String fileContent = "";

		for (int i = 0; i < baord.getHieght(); i++) {
			for (int j = 0; j < baord.getWidth(); j++) {
				fileContent += baord.getXY(j, i);
			}

			if (i != baord.getHieght() - 1) {
				fileContent += "/n";
			}
		}

		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(filePath);
			stream.write(fileContent.getBytes());
			stream.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}
