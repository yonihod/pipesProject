package pipelineServer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class FileCacheManager implements CacheManager {
	
	HashMap<String, String> solutions = new HashMap<String, String>();

	public String getSolution(String game) {
		
		String solution = solutions.get(game);
		
		if(solution != null) {
			return solution;
		}
		
		int c = -1;
		String fileContent = "";

		try {
			FileInputStream stream = new FileInputStream(game);
			while ((c = stream.read()) != -1) {
				char currentChar = (char) c;
				fileContent += currentChar;
			}
			stream.close();

			return fileContent;
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}

	}

	public void setSolution(String game, String solution) {
		solutions.put(game, solution);
		
		try {
			FileOutputStream stream = new FileOutputStream(game);
			stream.write(solution.getBytes());
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
