package synchronization_tool_class.semaphore;

import javax.script.ScriptEngineManager;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore为容器设置边界
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound){
        this.set= Collections.synchronizedSet(new HashSet<>());
        sem=new Semaphore(bound);//信号量初始化为容器容量的最大值
    }

    public boolean add(T o) throws InterruptedException{
        sem.acquire();//add之前先获取一个许可
        boolean wasAdded =false;
        try{
            wasAdded=set.add(o);
            return wasAdded;
        }finally {
            if(!wasAdded)//如果add操作没有添加任何元素，那么会立即释放许可
                sem.release();
        }
    }

    public boolean remove(Object o){
        boolean wasRemoved = set.remove(o);
        if(wasRemoved) sem.release();//remove 操作 释放许可
        return wasRemoved;
    }
}
/**
 * 可以使用Semphore将任何一种容器变成有界阻塞容器
 * 底层的Set实例并不知道关于边界的任何信息，由BoundedHashSet<T>来处理
 */
