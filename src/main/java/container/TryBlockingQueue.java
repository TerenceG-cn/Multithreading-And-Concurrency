package container;

import java.util.concurrent.*;

public class TryBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        final int capcity = 10;
        BlockingQueue<Integer> lbq = new LinkedBlockingQueue<>();//最好指定容量，默认MAX_VALUE
        BlockingQueue<Integer> alq = new ArrayBlockingQueue<>(capcity);
        BlockingQueue<Integer> synq = new SynchronousQueue<>();// to do
        lbq.put(1);
        lbq.take();
        alq.add(1);
        alq.poll();
        /*
        *注意5:SynchronousQueue的以下方法： 
        * iterator() 永远返回空，因为里面没东西。 
        * peek() 永远返回null。 
        * put() 往queue放进去一个element以后就一直wait直到有其他thread进来把这个element取走。 
        * offer() 往queue里放一个element后立即返回，如果碰巧这个element被另一个thread取走了，offer方法返回true，认为offer成功；否则返回false。 
        * offer(2000, TimeUnit.SECONDS) 往queue里放一个element但是等待指定的时间后才返回，返回的逻辑和offer()方法一样。 
        * take() 取出并且remove掉queue里的element（认为是在queue里的。。。），取不到东西他会一直等。 
        * poll() 取出并且remove掉queue里的element（认为是在queue里的。。。），只有到碰巧另外一个线程正在往queue里offer数据或者put数据的时候，该方法才会取到东西。否则立即返回null。 
        * poll(2000, TimeUnit.SECONDS) 等待指定的时间然后取出并且remove掉queue里的element,其实就是再等其他的thread来往里塞。 
        * isEmpty()永远是true。 
        * remainingCapacity() 永远是0。 
        * remove()和removeAll() 永远是false。 
        */
    }
}
