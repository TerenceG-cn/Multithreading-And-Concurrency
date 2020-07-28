package resultCache;

import net.jcip.annotations.GuardedBy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Memoizer2<A,V> implements Computable<A,V> {
    private final Map<A,V> cache = new ConcurrentHashMap<A, V>();
    private final Computable<A,V> c;

    public Memoizer2(Computable<A,V> c){
        this.c=c;
    }
    //计算缓存，已存在返回原值
    public V compute(A arg) throws InterruptedException{
        V result=cache.get(arg);
        if(result==null){
            result=c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}
/**
 * 两个线程可能会计算得到相同的值，使用FutureTask可以表示“线程x正在计算f(27)”这种情况
 */
