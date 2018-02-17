import java.util.List;
import java.util.Random;

import algorithms.Searchable;
import algorithms.Searcher;
import algorithms.Solution;
import algorithms.State;
import algorithms.StateGrader;

public class HillClimbing implements Searcher<char[][]> {
	long timeToRun = 10000; // TODO: use CTOR setter
	StateGrader<char[][]> grader; // TODO: use a CTOR setter

	@Override
	public Solution search(Searchable<char[][]> searchable) {
		State<char[][]> next = searchable.getInitialState();

		long time0 = System.currentTimeMillis();
		while (System.currentTimeMillis() - time0 < timeToRun) {
//			List<State<char[][]>> neighbors = searchable.getPossibleStates(next);
//
//			if (Math.random() < 0.7) { // with a high probability find the best one
//				int grade = 0;
//				for (State<char[][]> s : neighbors) {
//					int g = grader.grade(s);
//					if (g > grade) {
//						grade = g;
//						next = s;
//
//						// ToDo: add this step to the solution
//					}
//				}
//			} else {
//				next = neighbors.get(new Random().nextInt(neighbors.size()));
//			}
		}
		return null; // ToDo: return the solution
	}

}
