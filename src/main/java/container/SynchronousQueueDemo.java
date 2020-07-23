package container;

import java.util.concurrent.SynchronousQueue;

/**
 * Java Program to solve Producer Consumer problem using SynchronousQueue. A
 * call to put() will block until there is a corresponding thread to take() that
 * element.
 *
 * @author Javin Paul
 */
public class SynchronousQueueDemo{

    public static void main(String args[]) {

        final SynchronousQueue<String> queue = new SynchronousQueue<String>();  //如果引用为引用数据类型，
                                                                                // 比如对象、数组，则该对象、数组本身可以修改，
                                                                                // 但指向该对象或数组的地址的引用不能修改。

        Thread producer = new Thread("PRODUCER") {
            public void run() {
                String event = "FOUR";
                try {
                    queue.put(event); // thread will block here
                    System.out.printf("[%s] published event : %s %n", Thread
                            .currentThread().getName(), event);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        producer.start(); // starting publisher thread

        Thread consumer = new Thread("CONSUMER") {
            public void run() {
                try {
                    String event = queue.take(); // thread will block here
                    System.out.printf("[%s] consumed event : %s %n", Thread
                            .currentThread().getName(), event);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        consumer.start(); // starting consumer thread

    }

}

//Output:
//        [CONSUMER] consumed event : FOUR
//        [PRODUCER] published event : FOUR
//先启动producer，生产”event“后阻塞，再启动consume，
// 消费”event“(PRODUCER] published event : FOUR)后producer唤醒([CONSUMER] consumed event : FOUR)