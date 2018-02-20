package pipelineServer;

import java.util.HashMap;

public class SolutionCacheManager implements CacheManager {

	HashMap<String, String> solutions = new HashMap<String, String>();

	public String getSolution(String game) {
		return solutions.get(game);
	}

	public void setSolution(String game, String solution) {
		solutions.put(game, solution);
	}

}
