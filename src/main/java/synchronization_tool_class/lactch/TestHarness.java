package synchronization_tool_class.lactch;

import java.util.concurrent.CountDownLatch;

/**
 * 5-11 在计时测试中使用CountDownLatch来启动和停止线程
 */
public class TestHarness {
    public long timeTasks(int nThreads,final Runnable task)
        throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);//“起始门”
        final CountDownLatch endGate = new CountDownLatch(nThreads);//“结束门”

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        startGate.await();//等待计数器达到0
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();//递减计数器
                        }
                    } catch (InterruptedException ignored) {

                    }
                }
            };
            t.start();
        }
        long start=System.nanoTime();//纳秒,返回当前JVM的高精度时间。该方法只能用来测量时段而和系统时间无关。它的返回值是从某个固定但随意的时间点开始的（可能是未来的某个时间）。不同的JVM使用的起点可能不同。
        startGate.countDown();//同时释放线程
        endGate.await();//等待最后一个线程执行结束
        long end = System.nanoTime();
        return end-start;
    }
}
