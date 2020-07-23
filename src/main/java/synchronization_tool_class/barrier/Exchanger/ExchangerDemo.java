package synchronization_tool_class.barrier.Exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<Object> exchanger = new Exchanger<>();

        new Thread(() -> {
            Object object = new Object();
            System.out.println(Thread.currentThread().getName() + "创建的对象是" + object);
            try {
                object = exchanger.exchange(object);
                System.out.println(Thread.currentThread().getName() + "交换后得到的对象是" + object);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程1").start();

        new Thread(() -> {
            Object object = new Object();
            System.out.println(Thread.currentThread().getName() + "创建的对象是" + object);
            try {
                TimeUnit.SECONDS.sleep(2);
                object = exchanger.exchange(object);
                System.out.println(Thread.currentThread().getName() + "交换后得到的对象是" + object);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程2").start();
    }
}
