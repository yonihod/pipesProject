package model;

import java.io.InputStream;

public interface LevelLoader {
	PipeGameBoard loadLevel(String path);
}
