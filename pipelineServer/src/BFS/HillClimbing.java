package BFS;

import java.util.*;
import algorithms.*;

public class HillClimbing<T> extends CommonSearcher<T> {

	Stack<State<T>> stack = new Stack<>();
	long timeToRun = 0;
	
	public HillClimbing(long timeToRun) {
		this.timeToRun = timeToRun;
	}
	
	@Override
	public Solution search(Searchable<T> s) {
		State<T> next = s.getInitialState();

		long time0 = System.currentTimeMillis();
		while (System.currentTimeMillis() - time0 < timeToRun) {
			
			List<State<T>> neighbors = s.getAllPossibleStates(next);

			if (Math.random() < 0.7) { // with a high probability find the best one
				double grade = 0;
				for (State<T> state : neighbors) {
					double g = state.getCost();
					if (g > grade) {
						grade = g;
						next = state;

						// ToDo: add this step to the solution
					}
				}
			} else {
				next = neighbors.get(new Random().nextInt(neighbors.size()));
			}
		}
		return null; // ToDo: return the solution
	}

	private Solution<T> backTrace(State<T> goalState, State<T> initialState) {
		ArrayList<T> list = new ArrayList<T>();
		list.add(goalState.getState());
		State<T> currentState = goalState;

		while (currentState.getCameFrom() != null) {
			list.add(currentState.getCameFrom().getState());
			currentState = currentState.getCameFrom();
		}

		Solution<T> solution = new Solution<T>();
		solution.setSolution(list);
		return solution;
	}

}
