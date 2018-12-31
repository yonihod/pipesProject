package pipelineServer;

public class ConcurrentRunMyServer {

	public static void main(String[] args) {
        int port = 6400;
		if(args.length >= 1 ) {
            port = Integer.parseInt(args[0]);
        }
        ServerWithThreadPool myServer = new ServerWithThreadPool(port);
        SolutionCacheManager cachManager = new SolutionCacheManager();
        LevelSolver solver = new LevelSolver();
        ClientHandler clientHandler = new PriorityPipedGameClientHandler(cachManager, solver);
        myServer.start(clientHandler);
	}
}
