package waitsignal;

public class ResourceDemo {

    public static void main(String[] args) {
        //资源对象
        Resource r = new Resource();
        //任务对象
        Input in = new Input(r);
        Output out = new Output(r);
        //线程对象
        Thread t1 = new Thread(in);
        Thread t2 = new Thread(out);
        //开启线程
        t1.start();
        t2.start();
    }
}

