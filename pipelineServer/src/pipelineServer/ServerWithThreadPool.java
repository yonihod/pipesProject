package pipelineServer;

import pipelineServer.scheduler.Request;
import pipelineServer.scheduler.RequestScheduler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWithThreadPool implements Server {

	private int port;
	private volatile boolean stop = false;
	private ServerSocket server;
	private Socket sClient;

	public ServerWithThreadPool(int port) {
		this.port = port;
	}

	Socket getsClient() {
		return sClient;
	}

	void setsClient(Socket sClient) {
		this.sClient = sClient;
	}

	private void runServer() {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			 System.out.println("Can't listen on " + port);
			System.exit(1);
		}
	}

	@Override
	public void start(ClientHandler clientHandler) {
		new Thread(new Runnable() {

			@Override
			public void run() {
					runServer();
					System.out.println("started server on port "+port);
					while (!stop) {
						try {
							sClient = server.accept();
							clientHandler.handleClient(sClient.getInputStream(), sClient.getOutputStream());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					try {
						server.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("server shut down");

			}
		}).start();
	}

	@Override
	public void stop() {
		stop = true;
	}

}
