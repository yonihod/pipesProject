package pipelineServer;

import java.util.HashMap;

public class SolutionCacheManager implements CacheManager {

	HashMap<String, String> solutions = new HashMap<String, String>();
	
//	String game = "s------L\nL-|--|-L\nL------L\nL--|---g\n";
//	String solution = "0,1,1/n0,2,2/n1,1,2";

	public SolutionCacheManager() {
	//	solutions.put(game, solution);
	}

	public String getSolution(String game) {
		return solutions.get(game);
	}

	public void setSolution(String game, String solution) {
		solutions.put(game, solution);
	}

}
