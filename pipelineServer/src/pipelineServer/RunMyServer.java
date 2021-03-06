package pipelineServer;

import java.io.IOException;

public class RunMyServer {

	public static void main(String[] args) {
        int port = 6400;
		if(args.length >= 1 ) {
            port = Integer.parseInt(args[0]);
        }

        MyServer myServer = new MyServer(port);
        SolutionCacheManager cachManager = new SolutionCacheManager();
        LevelSolver solver = new LevelSolver();
        ClientHandler clientHandler = new PipeGameClientHandler(cachManager, solver);
        myServer.start(clientHandler);
	}
}
