package resultCache;

import java.util.Map;
import java.util.concurrent.*;

import static synchronization_tool_class.lactch.Preloader.launderThrowable;

public class Memoizer3<A, V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    //计算缓存，已存在返回原值
    public V compute(final A arg) throws InterruptedException { //Variable 'arg' is accessed from within inner class, needs to be declared final
        while (true) {                                          //内部类中使用但未声明的任何局部变量必须在内部类的正文之前明确分配。
            Future<V> future = cache.get(arg);
            if (future == null) {
                Callable<V> eval = new Callable<V>() {
                    public V call() throws InterruptedException {
                        return c.compute(arg);  // java中规定，内部类只能访问外部类中的成员变量，不能访问方法中定义的变量，
                                                // 如果要访问方法中的变量，就要把方法中的变量声明为final（常量）的，
                                                // 因为这样可以使变量全局化，就相当于是在外部定义的而不是在方法里定义的
                    }
                };
                FutureTask<V> ft = new FutureTask<V>(eval);
                future = cache.putIfAbsent(arg, ft);
                if (future == null) {
                    future = ft;
                    ft.run();
                }
            }
            try{
                return future.get();
            }catch (CancellationException e){
                cache.remove(arg,future);
            }catch (ExecutionException e){
                throw launderThrowable(e.getCause());
            }
        }
    }
}
