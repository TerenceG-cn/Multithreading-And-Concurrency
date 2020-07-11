package interrupt;

/**
 * 线程中断演示3
 * 另一个常用的中断线程的方法是设置标志位。
 * 通过标志位判断需要正确使用volatile关键字；
 * 我们通常会用一个running标志位来标识线程是否应该继续运行，在外部线程中，通过把HelloThread.running置为false，就可以让线程结束：
 */
public class ThreadInterrupt3 {
    public static void main(String[] args)  throws InterruptedException {
        HelloThread2 t = new HelloThread2();
        t.start();
        Thread.sleep(1);
        t.running = false; // 标志位置为false
    }
}

class HelloThread2 extends Thread {
    public volatile boolean running = true;//通过标志位判断需要正确使用volatile关键字；
    public void run() {
        int n = 0;
        while (running) {
            n ++;
            System.out.println(n + " hello!");
        }
        System.out.println("end!");
    }
}