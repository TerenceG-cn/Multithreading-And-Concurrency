package example;

import java.util.concurrent.CompletableFuture;

public class ThreadExeFour {
    public static void main(String[] args) {
        Thread t1=new Thread(new Work());
        Thread t2=new Thread(new Work());
        Thread t3=new Thread(new Work());
        //todo 真没见过
        CompletableFuture.runAsync(()->t1.start()).thenRun(()->t2.start()).thenRun(()->t3.start());
    }

    static class Work implements Runnable{
        @Override
        public void run(){
            System.out.println("thread start: "+Thread.currentThread().getName());
        }
    }
}
