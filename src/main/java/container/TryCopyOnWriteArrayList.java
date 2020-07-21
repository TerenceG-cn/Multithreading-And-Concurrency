package container;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TryCopyOnWriteArrayList {
    public static void main(String[] args){
        List<Integer> cop=new CopyOnWriteArrayList<>();
    }
}
    /**

     final transient Object lock=new Object();
     final void setArray(Object[] a) { array = a; }

     public boolean add(E e) {
        synchronized (lock) {//加锁
            Object[] es = getArray();
            int len = es.length;
            es = Arrays.copyOf(es, len + 1);//新建数组
            es[len] = e;
            setArray(es);//原来的引用指向新数组es
            return true;
        }
     }
     */