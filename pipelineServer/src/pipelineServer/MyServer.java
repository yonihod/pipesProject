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

					while (!stop) {
						try {
						sClient = server.accept();
						sClient.setSoTimeout(3000);
						ch.handleClient(sClient.getInputStream(), sClient.getOutputStream());
						sClient.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				
				try {
					server.close();

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
