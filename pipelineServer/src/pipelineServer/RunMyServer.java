package pipelineServer;

import java.io.IOException;

public class RunMyServer {

	public static void main(String[] args) {
		MyServer myServer = new MyServer(10007);
		SolutionCacheManager cachManager= new SolutionCacheManager();
		ClientHandler clientHandler = new PipeGameClientHandler(cachManager);
		myServer.start(clientHandler);
	}
}
