package model;

import java.util.HashMap;

public class FileAdapter implements LevelLoader, LevelSaver {

	HashMap<String, LevelLoader> fileLoaderMap = new HashMap<String, LevelLoader>();
	HashMap<String, LevelSaver> fileSaverMap = new HashMap<String, LevelSaver>();

	public FileAdapter() {
		fileLoaderMap.put("txt", new MyTextLevelLoader());
		fileLoaderMap.put("xml", new MyXMLLevelLoader());
		fileLoaderMap.put("obj", new MyObjectLevelLoader());

		fileSaverMap.put("txt", new MyTextLevelSaver());
		fileSaverMap.put("xml", new MyXMLLevelSaver());
		fileSaverMap.put("obj", new MyObjectLevelSaver());
	}
	
	private LevelLoader fileToLoader(String path) {
		if(path.contains("txt")) {
			return fileLoaderMap.get("txt");
		}
		if(path.contains("xml")) {
			return fileLoaderMap.get("xml");
		}
		if(path.contains("obj")) {
			return fileLoaderMap.get("obj");
		}
		return null;
	}
	
	private LevelSaver fileToSaver(String path) {
		if(path.contains("txt")) {
			return fileSaverMap.get("txt");
		}
		if(path.contains("xml")) {
			return fileSaverMap.get("xml");
		}
		if(path.contains("obj")) {
			return fileSaverMap.get("obj");
		}
		return null;
	}

	@Override
	public boolean SaveLevel(PipeGameBoard board, String filePath) {
		return fileToSaver(filePath).SaveLevel(board, filePath);
	}

	@Override
	public PipeGameBoard loadLevel(String path) {
		return fileToLoader(path).loadLevel(path);
	}

}
