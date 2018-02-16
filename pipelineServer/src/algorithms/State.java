package algorithms;

public class State<T>{
	T state;
	State<T> cameFrom;
	double cost;

	T getState() {
		return state;
	}

	void setState(T state) {
		this.state = state;
	}

	State<T> getCameFrom() {
		return cameFrom;
	}

	void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}

	double getCost() {
		return cost;
	}

	void setCost(double cost) {
		this.cost = cost;
	}

	public boolean equals(State<T> state) {
		return state.toString().equals(this.state.toString());}

	public int hashcode() {
		return this.getState().hashCode();
		
	}

}
