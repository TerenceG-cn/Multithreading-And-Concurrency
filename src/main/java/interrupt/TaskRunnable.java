package interrupt;

import java.util.concurrent.BlockingQueue;

public class TaskRunnable<Task> implements  Runnable{
    BlockingQueue<Task> queue;
    //...
    public void run(){
        try{
            processTask(queue.take());
        }catch (InterruptedException e){
            //回复被中断的状态
            Thread.currentThread().interrupt();
        }
    }

    private void processTask(Task take) {
    }
}
