package pipelineServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class PipeGameClientHandler implements ClientHandler {
	CacheManager cacheManager;

	public PipeGameClientHandler(CacheManager cManager) {
		cacheManager = cManager;
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
				System.out.println("Server: " + inputLine);
				if (inputLine.toLowerCase().equals("done")) {
					solution = cacheManager.getSolution(gameBoard);
					if (solution != null) {
						System.out.println(solution);
						output.println(solution);
					}
					System.out.println("done");
					output.println("done");

				} else {
					gameBoard += inputLine;
					gameBoard += "\n";
					// output.println(inputLine);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
