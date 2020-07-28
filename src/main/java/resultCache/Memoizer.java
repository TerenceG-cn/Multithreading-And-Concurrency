package resultCache;

import jdk.vm.ci.aarch64.AArch64;
import net.jcip.annotations.GuardedBy;

import java.util.HashMap;
import java.util.Map;

public class Memoizer<A,V> implements Computable<A,V> {
    @GuardedBy("this")//受对象内部锁保护
    private final Map<A,V> cache = new HashMap<A, V>();
    private final Computable<A,V> c;

    public Memoizer(Computable<A,V> c){
        this.c=c;
    }
    //计算缓存，已存在返回原值
    public synchronized V compute(A arg) throws InterruptedException{
        V result=cache.get(arg);
        if(result==null){
            result=c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
    /**
     * 因为使用synchronized对compute方法加锁，每次只有一个线程能够执行计算
     */
}
