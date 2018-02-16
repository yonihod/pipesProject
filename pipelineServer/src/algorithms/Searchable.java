package algorithms;

import java.util.ArrayList;

public interface Searchable<T> {
	State<T> getInitialState();
	ArrayList<State<T>>getPossibleStates(State<T> s);
	boolean isGoalState(State<T> s);
}
