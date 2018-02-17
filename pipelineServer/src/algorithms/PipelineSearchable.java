package algorithms;

import java.util.*;

public class PipelineSearchable implements Searchable<PipelineState> {

	State<PipelineState> initialState;
	State<PipelineState> goalState;
	char[][] board;
	ArrayList<State<PipelineState>> possibleStates = new ArrayList();
	private Map<String, State<PipelineState>> states = new HashMap<String, State<PipelineState>>();

	HashMap<String, String[]> charToDirection = new HashMap<String, String[]>() {
		{
			put("-", new String[] { "left", "right" });
			put("|", new String[] { "up", "down" });
			put("7", new String[] { "right", "up" });
			put("J", new String[] { "down", "right" });
			put("L", new String[] { "left", "down" });
			put("F", new String[] { "up", "left" });
			put("s", new String[] { "down", "right", "left", "up" });
			put("g", new String[] { "down", "right", "left", "up" });
		}
	};

	HashMap<String, Integer> costOfChange = new HashMap<String, Integer>() {
		{
			put("-,|", 1);
			put("|,-", 1);

			put("7,J", 1);
			put("7,L", 2);
			put("7,F", 3);

			put("J,L", 1);
			put("J,F", 2);
			put("J,7", 3);

			put("L,F", 1);
			put("L,7", 2);
			put("L,J", 3);

			put("F,7", 1);
			put("F,J", 2);
			put("F,L", 3);

		}
	};

	HashMap<String, char[]> charToOptions = new HashMap<String, char[]>() {
		{
			put("right,-", new char[] { '-' });
			put("right,|", new char[] { '-' });
			put("right,L", new char[] { 'L', 'F' });
			put("right,F", new char[] { 'L', 'F' });
			put("right,7", new char[] { 'L', 'F' });
			put("right,J", new char[] { 'L', 'F' });
			put("right,s", new char[] {});
			put("right,g", new char[] { 'g' });

			put("left,-", new char[] { '-' });
			put("left,|", new char[] { '-' });
			put("left,L", new char[] { 'J', '7' });
			put("left,F", new char[] { 'J', '7' });
			put("left,7", new char[] { 'J', '7' });
			put("left,J", new char[] { 'J', '7' });
			put("left,s", new char[] {});
			put("left,g", new char[] { 'g' });

			put("up,-", new char[] { '|' });
			put("up,|", new char[] { '|' });
			put("up,L", new char[] { 'L', 'J' });
			put("up,F", new char[] { 'L', 'J' });
			put("up,7", new char[] { 'L', 'J' });
			put("up,J", new char[] { 'L', 'J'});
			put("up,s", new char[] {});
			put("up,g", new char[] { 'g' });

			put("down,-", new char[] { '|' });
			put("down,|", new char[] { '|' });
			put("down,L", new char[] { '7', 'F' });
			put("down,F", new char[] { '7', 'F' });
			put("down,7", new char[] { '7', 'F' });
			put("down,J", new char[] { '7', 'F' });
			put("down,s", new char[] {});
			put("down,g", new char[] { 'g' });

		}
	};

	public PipelineSearchable(char[][] board) {
		this.board = board;
	}

	public void setInitialState(State<PipelineState> initialState) {
		this.initialState = initialState;
	}

	@Override
	public State<PipelineState> getInitialState() {
		return initialState;
	}

	@Override
	public State<PipelineState> getGoalState() {
		return goalState;
	}

	public void setGoalState(State<PipelineState> goal) {
		this.goalState = goal;
	}

	@Override
	public ArrayList<State<PipelineState>> getAllPossibleStates(State<PipelineState> s) {
		possibleStates = new ArrayList();
		Integer x = s.getState().getX();
		Integer y = s.getState().getY();

		char sourceChar = s.getState().getValue();
		String[] allowedDirections = charToDirection.get(Character.toString(sourceChar));
		
		boolean canUp = false, canDown = false, canLeft = false, canRight = false;

		for (int i = 0; i < allowedDirections.length; i++) {
			switch (allowedDirections[i]) {
			case "left": {
				canLeft = true;
				break;
			}
			case "right": {
				canRight = true;
				break;
			}
			case "up": {
				canUp = true;
				break;
			}
			case "down": {
				canDown = true;
				break;
			}
			}
		}

		//
		// String[] directions = charToDirection.get(value);
		//
		// for(int i = 0; i < directions.length; i++) {
		// switch (directions[i]) {
		// case "down":
		// moveDown(s);
		// break;
		//
		// default:
		// break;
		// }
		// }

		if (x >= 0 && x < board[0].length - 1 && canLeft) {
			if (board[y][x + 1] != ' ' && !checkIfCameFrom(x+1, y, s.getCameFrom())) {
				char targetChar = board[y][x + 1];
				char[] changeTo = charToOptions.get("left," + targetChar);

				for (int i = 0; i < changeTo.length; i++) {
					Integer cost = costOfChange
							.get(Character.toString(targetChar) + "," + Character.toString(changeTo[i]));
					possibleStates.add(getState(x + 1, y, changeTo[i], cost));
				}
			}
		}
		if (x != 0 && x <= board[0].length - 1 && canRight) {
			if (board[y][x - 1] != ' ' && !checkIfCameFrom(x-1, y, s.getCameFrom())) {
				char targetChar = board[y][x - 1];
				char[] changeTo = charToOptions.get("right," + targetChar);

				for (int i = 0; i < changeTo.length; i++) {
					Integer cost = costOfChange
							.get(Character.toString(targetChar) + "," + Character.toString(changeTo[i]));
					possibleStates.add(getState(x - 1, y, changeTo[i], cost));
				}

			}
		}

		if (y >= 0 && y < board.length - 1 && canUp) {
			if (board[y + 1][x] != ' ' && !checkIfCameFrom(x, y + 1, s.getCameFrom())) {
				char targetChar = board[y + 1][x];
				char[] changeTo = charToOptions.get("up," + targetChar);

				for (int i = 0; i < changeTo.length; i++) {
					Integer cost = costOfChange
							.get(Character.toString(targetChar) + "," + Character.toString(changeTo[i]));
				
					possibleStates.add(getState(x, y + 1, changeTo[i], cost));
				}
			}
		}
		if (y != 0 && y <= board.length - 1 && canDown) {
			if (board[y - 1][x] != ' ' && !checkIfCameFrom(x, y - 1, s.getCameFrom())) {
				char targetChar = board[y - 1][x];
				char[] changeTo = charToOptions.get("down," + targetChar);

				for (int i = 0; i < changeTo.length; i++) {
					Integer cost = costOfChange
							.get(Character.toString(targetChar) + "," + Character.toString(changeTo[i]));
					possibleStates.add(getState(x, y - 1, changeTo[i], cost));
				}
			}
		}

		return possibleStates;
	}

	private State<PipelineState> getState(Integer x, Integer y, char value, Integer cost) {
		String key = y.toString() + "," + x.toString() + " , " + Character.toString(value);

		if (cost == null) {
			cost = 0;
		}

		if (states.containsKey(key)) {
			return states.get(key);
		} else {
			PipelineState p1 = new PipelineState(x, y, value, cost);
			State<PipelineState> s1 = new State<PipelineState>();
			s1.setState(p1);
			s1.setCost(cost);
			states.put(key, s1);
			return s1;
		}
	}
	
	private boolean checkIfCameFrom(Integer targetX, Integer targetY, State<PipelineState> cameFrom) {
		if(cameFrom != null && cameFrom.getState() != null) {
			PipelineState cameFromState = cameFrom.getState();
			if(cameFromState.getX() == targetX && cameFromState.getY() == targetY) {
				return true;
			}
		}
		
		return false;
	}

}
