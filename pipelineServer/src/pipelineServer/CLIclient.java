package pipelineServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CLIclient {
	private void readInputAndSend(BufferedReader in, PrintWriter out, String exitstr) {
		String line;
		try {
			while (!(line = in.readLine()).equals(exitstr)) {
				out.println(line);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start(String ip, int port) {
		try {
			Socket theServer = new Socket(ip, port);
			//System.out.println("connected to server");

			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(theServer.getInputStream()));

			PrintWriter outToServer=new PrintWriter(theServer.getOutputStream());
			PrintWriter outToScreen=new PrintWriter(System.out);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
