package pipelineServer;

import java.io.*;

public interface ClientHandler {
	void handleClient(InputStream inFromClient, OutputStream outToClient);
}
