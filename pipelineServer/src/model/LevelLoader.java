package model;

import java.io.InputStream;

public interface LevelLoader {
	Level loadLevel(InputStream stream);
}
