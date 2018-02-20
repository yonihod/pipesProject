package pipelineServer;

public class testServer {
	public static void main(String[] args) {
		LevelSolver ls = new LevelSolver();

		String gameBoard = "s------L\nL-|--|-L\nL------L\nL--|---g";
		String gameBoard2 = "s  \n-  \nLg ";

		String solved = ls.Solve(gameBoard2);
		
	//	System.out.println("Solution: " + solved);
	}
}
