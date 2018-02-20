package pipelineServer;

import java.io.*;
import java.net.*;

import model.FileAdapter;
import model.MyTextLevelLoader;
import model.PipeGameBoard;

public class MyClient {

	public static void main(String[] args) throws IOException {
		
		
//		int a = 1;
		
//		if(a == 1) {
//			MyTextLevelLoader txtLoader = new MyTextLevelLoader();
//			PipeGameBoard l1 =  txtLoader.loadLevel("/Users/valeriamadaev/temp/l.txt");
//	
//			FileAdapter fa = new FileAdapter();
//			fa.SaveLevel(l1, "/Users/valeriamadaev/temp/l2.xml");
//			
//			PipeGameBoard l2 = fa.loadLevel("/Users/valeriamadaev/temp/l2.xml");
//			fa.SaveLevel(l2, "/Users/valeriamadaev/temp/l3.txt");
//		} else {
		

		Socket sock = null;
		PrintWriter output = null;
		BufferedReader input = null;

		try {
			sock = new Socket("127.0.0.1", 10007);
			output = new PrintWriter(sock.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		} catch (UnknownHostException e) {
			//System.out.println("Unknown host");
			System.exit(1);
		} catch (IOException ie) {
			//System.out.println("Cannot connect to host");
			System.exit(1);
		}
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String userInput;
		String inputLine;
		// System.out.println("Waiting for input");
		output.println("s--7");
		output.println("L-|g");
		// output.println(" -fJ");
		// output.println(" 7g ");
		// output.println("s ");
		// output.println("- ");
		// output.println("Lg ");
		output.println("done");
		while ((inputLine = input.readLine()) != null) {
			//System.out.println(inputLine);
			if (inputLine.equals("done"))
				break;
		}
		// while((userInput=stdIn.readLine())!=null) {
		// output.println(userInput);
		// System.out.println("myClient: "+input.readLine());
		// if(userInput.equals("Done"))
		// {
		// break;
		// }
		// }
		output.close();
		input.close();
		stdIn.close();
		sock.close();
//		}
	}
}
