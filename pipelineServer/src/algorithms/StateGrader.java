package algorithms;

public interface StateGrader<T> {
	int grade(State<T> s); //give a grade to a certain state - how close it to the solution
}
