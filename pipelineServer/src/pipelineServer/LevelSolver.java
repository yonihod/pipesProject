package pipelineServer;

import java.util.ArrayList;
import BFS.*;
import algorithms.*;
import model.Board;
import model.PipeGameBoard;

public class LevelSolver implements Solver{
	
	ArrayList<State<PipelineState>> boardState= new ArrayList<State<PipelineState>>();
	
	public String Solve(String game) {
		String[] list=game.split("\n");
		char[][] board = new char[list.length][];
		ArrayList<String> a;
		
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
		
				
		PipelineSearchable searchale =  new PipelineSearchable(new PipeGameBoard(board)); 
		searchale.setInitialState(startPoint);
		searchale.setGoalState(endPoint);
		
		BestFirstSearch<PipelineState> bfs = new BestFirstSearch<PipelineState>();
		Solution<PipelineState> solution = bfs.search(searchale);
		
		//DFS<PipelineState> dfs = new DFS<PipelineState>();
		//Solution<PipelineState> solution = dfs.search(searchale);
		
		//SearcherAdpater<PipelineState> searcher = new SearcherAdpater<>(board.length);
		//Solution<PipelineState> solution = searcher.search(searchale);
		
		if(solution != null) { 
			String moves = "";
			for(int i = solution.getSolution().size() -2; i >=1 ; i--) {
				PipelineState currentState = solution.getSolution().get(i);
				moves += currentState.getY() + "," + currentState.getX() + "," + currentState.getCost().toString();
				
				if(i != 1) {
					moves += "\n";
				}
			}
			return moves;
		} else {
			return "Can't solve this board";			
		}
		

	}
}
