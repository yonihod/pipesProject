package pipelineServer.scheduler;


import pipelineServer.CacheManager;
import pipelineServer.Solver;

import java.io.*;

public class Request implements Runnable {
    private int priority;
    Solver solver;
    CacheManager cacheManager;
    InputStream inputStream;
    OutputStream outputStream;
    String gameBoard;


    @Override
    public void run() {
        PrintWriter output = new PrintWriter(outputStream, true);
        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String solution = cacheManager.getSolution(gameBoard);
            if (solution != null) {
                output.println(solution);
            } else {
                String solved = solver.Solve(gameBoard);
                cacheManager.setSolution(gameBoard, solved);
                output.println(solved);
            }
            output.println("done");
            output.flush();
        }finally {
            output.close();
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Request(int priority, Solver solver, CacheManager cacheManager, InputStream inputStream, OutputStream outputStream, String gameBoard) {
        this.priority = priority;
        this.solver = solver;
        this.cacheManager = cacheManager;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.gameBoard = gameBoard;
    }

    public int getPriority() {
        return priority;
    }


}
