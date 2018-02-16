package model;

import java.beans.XMLDecoder;
import java.io.InputStream;

public class MyXMLLevelLoader implements LevelLoader {

	@Override
	public Level loadLevel(InputStream stream) {
		XMLDecoder d = new XMLDecoder(stream);
		Object o = d.readObject();
		d.close();
		return (Level) o;
	}

}
