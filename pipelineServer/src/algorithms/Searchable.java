package algorithms;

import java.util.ArrayList;
import java.util.List;

public interface Searchable<T> {
	State<T> getInitialState();

	State<T> getGoalState();

	ArrayList<State<T>> getAllPossibleStates(State<T> s);
}
