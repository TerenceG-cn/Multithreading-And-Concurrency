package container.blockqueue.producer_consumer;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class testMain {

    public static void main(String[] args) {
        int indexrCnt=0,fileCrawlerCnt=0;
        File file = new File("C:\\Program Files\\Java\\jdk-14");
        File[] roots = file.listFiles();
        final int BOUND = 10;
        final int N_CONSUMERS = Runtime.getRuntime().availableProcessors();//获得可用的处理器个数,这个值并不准确
        System.out.println("处理器个数："+N_CONSUMERS);
        BlockingQueue<File> queue = new LinkedBlockingQueue<File>(BOUND);
        //消费
        for (int i = 0; i < N_CONSUMERS; i++) {
            new Thread(new Indexer(queue)).start();
        }
        //生产
        for (File root : roots) {
            new Thread(new FileCrawler(queue, root)).start();
        }
    }

}
