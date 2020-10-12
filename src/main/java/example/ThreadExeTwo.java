package example;

import java.util.concurrent.CountDownLatch;

/**
 * 使用CountDownLatch来实现啊3个线程顺序执行
 */
public class ThreadExeTwo {
    final static CountDownLatch latch2 = new CountDownLatch(1);
    final static CountDownLatch latch3 = new CountDownLatch(1);

    public static void main(String[] args) {
        Thread t1=new Thread(new Work1());
        Thread t2=new Thread(new Work2());
        Thread t3=new Thread(new Work3());

        t3.start();t1.start();t2.start();

    }

    static class Work3 implements Runnable{

        @Override
        public void run() {

            try {
                latch3.await();
                System.out.println("子线程："+Thread.currentThread().getName()+"执行");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Work2 implements Runnable{

        @Override
        public void run() {
            try {
                latch2.await();
                System.out.println("子线程："+Thread.currentThread().getName()+"执行");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch3.countDown();
        }
    }

    static class Work1 implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println("子线程："+Thread.currentThread().getName()+"执行");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch2.countDown();
        }
    }
}
