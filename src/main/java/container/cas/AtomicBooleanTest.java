package container.cas;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author hrabbit
 * 2018/07/16.
 */
public class AtomicBooleanTest implements Runnable {

    private static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        AtomicBooleanTest ast = new AtomicBooleanTest();
        Thread thread0 = new Thread(ast);
        Thread thread1 = new Thread(ast);
        thread0.start();
        flag.set(true);
        Thread.sleep(1000);
        thread1.start();
    }
    @Override
    public void run() {
        System.out.println("thread:"+Thread.currentThread().getName()+";flag:"+flag.get());
        if (flag.compareAndSet(true,false)){
            System.out.println(Thread.currentThread().getName()+""+flag.get());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag.set(true);
        }else{
            System.out.println("重试机制thread:"+Thread.currentThread().getName()+";flag:"+flag.get());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            run();
        }

    }
}
