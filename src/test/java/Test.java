class Producer implements Runnable{
    private Integer resource;
    public Producer(Integer resource){}
    public void run(){
        synchronized(resource){
            while(resource>=10){
                try{
                    resource.wait();
                }catch(InterruptedException e){}
            }

            resource++;
            System.out.println("生产者产生资源");
            resource.notify();
        }
    }
}
class Consumer implements Runnable{
    private Integer resource;
    public Consumer(Integer resource){}
    public void run(){
        synchronized(resource){
            while(resource<=0){
                try{resource.wait();}catch(InterruptedException e){}
            }

            resource--;
            System.out.println("消费者消耗资源");
            resource.notify();
        }
    }
}

public class Test{
    public static void main(String[] args){
        Integer r=5;
        Thread p=new Thread(new Producer(r));
        Thread c=new Thread(new Consumer(r));
        p.start();c.start();

    }
}