package pipelineServer;

import pipelineServer.scheduler.Request;
import pipelineServer.scheduler.RequestScheduler;

import java.io.*;

public class PriorityPipedGameClientHandler implements ClientHandler {
	CacheManager cacheManager;
	Solver solver;
	private int POOL_SIZE = 5;
	private int QUEUE_SIZE = 10;
	private RequestScheduler requestScheduler;


	public PriorityPipedGameClientHandler(CacheManager cManager, Solver s) {
		cacheManager = cManager;
		this.solver=s;
		requestScheduler = new RequestScheduler(POOL_SIZE,QUEUE_SIZE);
	}

	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		
		PrintWriter output = new PrintWriter(outToClient, true);
		BufferedReader input = new BufferedReader(new InputStreamReader(inFromClient));

		String inputLine;
		String gameBoard = "";
		String solution = "";
		
		try {
			while ((inputLine = input.readLine()) != null) {

				if (inputLine.toLowerCase().equals("done")) {
					Request request = new Request(gameBoard.length(), solver, cacheManager, inFromClient, outToClient, gameBoard);
					requestScheduler.scheduleJob(request);
					break;
				} else {
					gameBoard += inputLine;
					gameBoard += "\n";
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
