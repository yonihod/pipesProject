package BFS;

import java.util.*;
import algorithms.*;

public class BFS<T> extends CommonSearcher<T> {

	@Override
	public Solution<T> search(Searchable<T> s) {
		addToOpenList(s.getInitialState());
		HashSet<State<T>> closedSet = new HashSet<State<T>>();

		while (openList.size() > 0) {
			State<T> n = popOpenList();
			closedSet.add(n);
			if (n.equals(s.getGoalState())){
				return backTrace(n, s.getInitialState());
			}

			ArrayList<State<T>> successors = s.getAllPossibleStates(n);
			for (State<T> state : successors) {
				if (!closedSet.contains(state) && !openListContains(state)) {
					state.setCameFrom(n);
					addToOpenList(state);

				}
			}

		}
		return null;
	}

	private boolean openListContains(State<T> state) {
		return openList.contains(state);
	}

	private Solution<T> backTrace(State<T> goalState, State<T> initialState) {
		ArrayList<T> list = new ArrayList<T>();
		list.add(goalState.getState());
		State<T> currentState = goalState;

		while(currentState.getCameFrom() != null){
			list.add(currentState.getCameFrom().getState());
			currentState = currentState.getCameFrom();
		}

		Solution<T> solution = new Solution<T>();
		solution.setSolution(list);

		return solution;
	}

	private void addToOpenList(State<T> initialState) {
		openList.add(initialState);
	}

}
