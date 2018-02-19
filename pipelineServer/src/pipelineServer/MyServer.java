package pipelineServer;

import java.io.*;
import java.net.*;

public class MyServer implements Server {

	private int port;
	private ClientHandler ch;
	private volatile boolean stop = false;
	private ServerSocket server;
	private Socket sClient;

	public MyServer(int port) {
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
			// System.out.println("Can't listen on " + port);
			System.exit(1);
		}
	}

	@Override
	public void start(ClientHandler clientHandler) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				ch = clientHandler;
				runServer();
				try {
					while (!stop) {
						// System.out.println("Listening for connection.");
						sClient = server.accept();
						// System.out.println("Connection successful");
						// System.out.println("Listening for input...");

						ch.handleClient(sClient.getInputStream(), sClient.getOutputStream());

						// System.out.println("Client ended.");
						sClient.close();
						// System.out.println("Client closed");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					server.close();
					// System.out.println("server is close");

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}).start();
	}

	@Override
	public void stop() {
		stop = true;
	}

}
