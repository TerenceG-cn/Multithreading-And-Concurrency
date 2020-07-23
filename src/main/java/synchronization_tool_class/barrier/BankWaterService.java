package synchronization_tool_class.barrier;

import java.util.Map.Entry;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 使用CyclicBarrier银行流水处理服务类
 *
 * @authorftf
 */
public class BankWaterService implements Runnable {
    /**
     * 创建4个屏障，处理完之后执行当前类的run方法
     */
    private final CyclicBarrier c = new CyclicBarrier(4, this);
    /**
     * 假设只有4个sheet，所以只启动4个线程
     */
    private final Executor executor = Executors.newFixedThreadPool(4);
    /**
     * 保存每个sheet计算出的银流结果
     */
    private final ConcurrentHashMap<String, Integer> sheetBankWaterCount = new
            ConcurrentHashMap<String, Integer>();

    private void count() {
        for (int i = 0; i < 4; i++) {
            /*executor.execute(new Runnable() {
                @Override
                public void run() {
                    //计算当前sheet的银流数据，计算代码省略
                    sheetBankWaterCount
                            .put(Thread.currentThread().getName(), 1);
                    System.out.println(Thread.currentThread().getName() + ",count");
                    //银流计算完成，插入一个屏障
                    try {
                        c.await();
                    } catch (InterruptedException |
                            BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });*/

            //与上面的run同义，使用了lambda
            executor.execute(() -> {
                //计算当前sheet的银流数据，计算代码省略,用1代替
                sheetBankWaterCount
                        .put(Thread.currentThread().getName(), 1);
                System.out.println(Thread.currentThread().getName() + ",count");
                //银流计算完成，插入一个屏障
                try {
                    c.await();
                } catch (InterruptedException |
                        BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    //所有线程都到达时，最后一个到达的线程触发Runnable运行
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ",run");
        int result = 0;
        //汇总每个sheet计算出的结果
        for (Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }
        //将结果输出
        sheetBankWaterCount.put("result", result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        BankWaterService bankWaterCount = new BankWaterService();
        bankWaterCount.count();
    }
}
