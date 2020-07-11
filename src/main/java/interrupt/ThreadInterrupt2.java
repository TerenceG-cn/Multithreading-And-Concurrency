package interrupt;

/**
 * 线程中断演示2
 * main线程通过调用t.interrupt()从而通知t线程中断，
 * 而此时t线程正位于hello.join()的等待中，此方法会立刻结束等待并抛出InterruptedException。
 */
public class ThreadInterrupt2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread2();
        t.start();
        Thread.sleep(1000);
        t.interrupt(); // 中断t线程
        System.out.println(t.isInterrupted());
        t.join(); // 等待t线程结束
        System.out.println("end");
    }
}

class MyThread2 extends Thread {
    public void run() {
        Thread hello = new HelloThread();
        hello.start(); // 启动hello线程
        try {
            hello.join(); // 等待hello线程结束
        } catch (InterruptedException e) {
            System.out.println("interrupted!");
        }
        //hello.interrupt();
    }
}

class HelloThread extends Thread {
    public void run() {
        int n = 0;
        while (!isInterrupted()) {
            n++;
            System.out.println(n + " hello!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}