package example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用newSingleThreadExecutor来实现3个线程顺序执行
 */
public class ThreadExeThree {
    public static void main(String[] args) {
        Thread t1=new Thread(new Work1());
        Thread t2=new Thread(new Work2());
        Thread t3=new Thread(new Work3());
        //单线程化线程池(newSingleThreadExecutor)会串行执行所有任务。
        // 如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。
        // 此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
        ExecutorService executor= Executors.newSingleThreadExecutor();
        executor.submit(t1);
        executor.submit(t2);
        executor.submit(t3);

        executor.shutdown();
    }

    static class Work1 implements Runnable{
        public void run(){
            System.out.println("thread1 start: "+Thread.currentThread().getName());
        }
    }
    static class Work2 implements Runnable{
        public void run(){
            System.out.println("thread2 start: "+Thread.currentThread().getName());
        }
    }
    static class Work3 implements Runnable{
        public void run(){
            System.out.println("thread3 start: "+Thread.currentThread().getName());
        }
    }
}
