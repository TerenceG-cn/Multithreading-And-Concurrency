package synchronization_tool_class.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    Semaphore binary = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {
        final SemaphoreDemo test = new SemaphoreDemo();
        new Thread(){
            @Override
            public void run(){
                //
                try {
                    //System.out.println("线程0启动！");
                    test.mutualExclusion();
                    System.out.println("线程0结束！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run(){

                try {
                    //System.out.println("线程1启动！");
                    test.mutualExclusion();
                    System.out.println("线程1结束！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void mutualExclusion() throws InterruptedException {
        try {
            //System.out.println(Thread.currentThread().getName() + " 未获取到信号量");
            binary.acquire();//获取binary信号量

            //mutual exclusive region
            System.out.println(Thread.currentThread().getName() + " inside mutual exclusive region");//在共享区
            Thread.sleep(1000);

        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } finally {
            binary.release();//释放信号量
            System.out.println(Thread.currentThread().getName() + " outside of mutual exclusive region");//出共享区
        }
    }
}
