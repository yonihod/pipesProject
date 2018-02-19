package BFS;

import java.util.*;
import algorithms.*;

public class DFS<T> extends CommonSearcher<T> {

	Stack<State<T>> stack = new Stack<>();
	@Override
	public Solution search(Searchable<T> s) {

		stack.add(s.getInitialState());
		HashSet<State<T>> closedSet = new HashSet<State<T>>();

		while (stack.size() > 0) {
			State<T> n = stack.pop();
			closedSet.add(n);
			
			if (n.equals(s.getGoalState())) {
				return backTrace(n, s.getInitialState());
			}

			ArrayList<State<T>> successors = s.getAllPossibleStates(n);
			for (State<T> state : successors) {
				if (!closedSet.contains(state) && !stack.contains(state)) {
					state.setCameFrom(n);
					stack.add(state);

				}
			}

		}
		return null;
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
