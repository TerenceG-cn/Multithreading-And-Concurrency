package container.blockqueue.producer_consumer;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * 文件爬虫
 */
public class FileCrawler implements Runnable{
    private static int fileCrawlerCnt=0;//记录数

    private final BlockingQueue<File> fileBlockingQueue;
    private final FileFilter fileFilter;//to do
    private final File root;

    public FileCrawler(BlockingQueue<File> fileBlockingQueue, File root) {
        this.fileBlockingQueue = fileBlockingQueue;
        this.root = root;
        this.fileFilter=new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return false;
            }
        };//to do
    }

    public void run(){
        System.out.println("FileCrawler"+(fileCrawlerCnt++)+"启动");
        try{
            crawl(root);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root) throws InterruptedException{
        File[] entries = root.listFiles();
        if(entries!=null){
            for(File entry:entries)
                if(entry.isDirectory())
                    crawl(entry);
                else if(!alreadyIndexed(entry)) {
                    System.out.println("生产者产生文件"+entry.getName());
                    fileBlockingQueue.put(entry);
                }
        }
    }

    /**
     * 是否已经被索引 to do
     * @param entry
     * @return
     * @author xingle
     * @data 2014-9-3 下午5:26:04
     */
    private boolean alreadyIndexed(File entry) {
        return fileBlockingQueue.contains(entry);
    }
}