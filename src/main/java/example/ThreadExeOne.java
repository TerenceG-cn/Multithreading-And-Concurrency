package example;

import java.io.IOException;

/**
 * 使用join实现3个线程顺序执行
 */
public class ThreadExeOne {
    public static void main(String[] args) {
        Thread t1=new Thread(new Work(null));
        Thread t2=new Thread(new Work(t1));
        Thread t3=new Thread(new Work(t2));

        t1.start();t2.start();t3.start();
    }

    static class Work implements Runnable{
        private Thread preThread;
        public Work(Thread preThread){
            this.preThread=preThread;
        }
        public void run(){
            if(preThread!=null){
                try{
                    preThread.join();//如果有线程就join
                    System.out.println("thread start: "+Thread.currentThread().getName());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("thread start: "+Thread.currentThread().getName());
            }
        }
    }
}
