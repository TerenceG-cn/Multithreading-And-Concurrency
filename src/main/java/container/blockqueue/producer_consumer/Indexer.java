package container.blockqueue.producer_consumer;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class Indexer implements  Runnable{
    private static int indexrCnt=0;//记录数

    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue){
        this.queue=queue;
    }

    @Override
    public void run() {
        System.out.println("indexr"+(indexrCnt++)+"启动");
        try{
            while (true){
                indexFile(queue.take());
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    /**
     * to do
     * @param file
     */
    private void indexFile(File file) {
        System.out.println("消费者取出文件："+file.getName()+"； 来自线程："+Thread.currentThread().getName());
    };
}
