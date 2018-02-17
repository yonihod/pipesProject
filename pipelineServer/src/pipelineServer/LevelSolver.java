package pipelineServer;

import java.util.ArrayList;

import BFS.BFS;
import algorithms.*;

public class LevelSolver {
	
	ArrayList<State<PipelineState>> boardState= new ArrayList<State<PipelineState>>();
	
	public String Solve(String game) {
		String[] list=game.split("\n");
		char[][] board = new char[list.length][];
		
		State<PipelineState> startPoint = null;
		State<PipelineState> endPoint = null;
		
		for(int y=0; y<list.length; y++)
		{
			for(int x=0; x<list[y].length();x++)
			{
				State<PipelineState> currentState= new State<PipelineState>();
				PipelineState pipelineState=new PipelineState(x,y,list[y].charAt(x), 0);
				currentState.setState(pipelineState);
				boardState.add(currentState);
				
				if(board[y] == null) {
					board[y] = new char[list[y].length()];
				}
				
				board[y][x] = list[y].charAt(x);
				
				if(list[y].charAt(x) == 's') {
					startPoint = currentState;
				} else if (list[y].charAt(x) == 'g') {
					endPoint = currentState;
				}
			}
		}
		
		
		PipelineSearchable searchale =  new PipelineSearchable(board); 
		searchale.setInitialState(startPoint);
		searchale.setGoalState(endPoint);
		
		BFS<PipelineState> bfs = new BFS<PipelineState>();
		Solution<PipelineState> solution = bfs.search(searchale);
		
		
		if(solution != null) { 
			String moves = "";
			for(int i = solution.getSolution().size() -1; i >=0 ; i--) {
				PipelineState currentState = solution.getSolution().get(i);
				moves += currentState.getY() + "," + currentState.getX() + "," + currentState.getCost().toString();
				
				if(i != 0) {
					moves += "\n";
				}
			}
			return moves;
		} else {
			return "Can't solve this board";			
		}
		

	}
}
