package pipelineServer.scheduler;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class RequestScheduler {


    private ExecutorService priorityJobPoolExecutor;
    private ExecutorService priorityJobScheduler = Executors.newSingleThreadExecutor();
    private PriorityBlockingQueue<Request> priorityQueue;
    volatile boolean  isRunning =true;
    public  RequestScheduler(Integer poolSize, Integer queueSize) {

        priorityJobPoolExecutor = Executors.newFixedThreadPool(poolSize);
        priorityQueue = new PriorityBlockingQueue<>(queueSize, Comparator.comparing(Request::getPriority));
        priorityJobScheduler.execute(() -> {
            while (isRunning) {
                try {
                    priorityJobPoolExecutor.execute(priorityQueue.take());
                } catch (InterruptedException e) {
                    // exception needs special handling
                    e.printStackTrace();
                    break;
                }
            }
        });
    }


    public void scheduleJob(Request request) {
        priorityQueue.add(request);
    }

    public void  stop(){
        isRunning =false;
    }
}
