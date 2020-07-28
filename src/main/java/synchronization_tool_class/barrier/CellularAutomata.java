package synchronization_tool_class.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 通过CyclicBarrier协调细胞自动衍生系统中的计算
 */
public class CellularAutomata {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        //this.barrier = new CyclicBarrier(count, mainBoard::commitNewValues);/*lambda表达式1*/
        //this.barrier = new CyclicBarrier(count, ()->mainBoard.commitNewValues());
        this.barrier = new CyclicBarrier(count, new Runnable() {
            public void run(){mainBoard.commitNewValues();}
        });
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {//线程数和初始化barrier时一样
            //分解成子问题
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
        }
    }
    //工作线程
    private class Worker implements Runnable {
        private final Board board;

        public Worker(Board board) { this.board = board; }
        @Override
        public void run() {
            while (!board.hasConverged()) {
                for (int x = 0; x < board.getMaxX(); x++) {
                    for (int y = 0; y < board.getMaxY(); y++) {
                        board.setNewValue(x, y, computeValue(x, y));
                    }
                }
                try {
                    //等待所有的线程都工作完后再往下执行
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException ex) {
                    return;
                }
            }
        }

        private int computeValue(int x, int y) {
            // Compute the new value that goes in (x,y)
            return 0;
        }
    }
    //主方法
    public void start() {
        //分解工作
        for (Worker worker : workers) {
            new Thread(worker).start();
        }
        //合并工作
        mainBoard.waitForConvergence();
    }

    interface Board {
        int getMaxX();
        int getMaxY();
        int getValue(int x, int y);
        int setNewValue(int x, int y, int value);
        void commitNewValues();
        boolean hasConverged();
        void waitForConvergence();
        Board getSubBoard(int numPartitions, int index);
    }
}

