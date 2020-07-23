package synchronization_tool_class.barrier;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Prepare implements Runnable {
    CyclicBarrier cb;
    public Prepare(CyclicBarrier cb) {
        this.cb = cb;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "准备完成");
            cb.await();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "中断");
        } catch (BrokenBarrierException e) {
            System.out.println(Thread.currentThread().getName() + "BrokenBarrierException");
            // 遇到栅栏损坏，重新来一句，原来到达的线程散伙，但是栅栏重置，当有新线程到达的时候，依旧可以开始
            cb.reset();
        }

    }
}

public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {//需要等待线程数5

            @Override
            public void run() {
                System.out.println("运动员到齐，开始跑");

            }
        });
        ArrayList<Thread> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Thread thread = new Thread(new Prepare(cyclicBarrier), "[第" + i + "运动员]");
            list.add(thread);
            thread.sleep(1000);
            thread.start();
            if (i == 3) {
                thread.interrupt();
            }
        }
        Thread.sleep(1000);
        System.out.println("栅栏损坏" + cyclicBarrier.isBroken());
    }

}

