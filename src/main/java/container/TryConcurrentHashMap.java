package container;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TryConcurrentHashMap {
    public static void main(String[] args){
        Map<Integer,Integer> concurrentHashMap = new ConcurrentHashMap<Integer, Integer>();
        //Map<Integer,Integer> hashmap=new HashMap<Integer, Integer>();
        /**
         * 额外的原子操作
         */
        concurrentHashMap.putIfAbsent(1,1);//V putIfAbsent(K key,V value)
        /*
            public V putIfAbsent(K key, V value) {
                return putVal(key, value, true);
            }
         */
        concurrentHashMap.remove(1,1 );
        /*
            public boolean remove(Object key, Object value) {
                if (key == null)
                    throw new NullPointerException();
                return value != null && replaceNode(key, null, value) != null;
            }
         */
        concurrentHashMap.replace(1, 1, 0);
        /*
            public boolean remove(Object key, Object value) {
                if (key == null)
                    throw new NullPointerException();
                return value != null && replaceNode(key, null, value) != null;
            }

         */
        concurrentHashMap.replace(1, 2);
        /*
        public boolean replace(K key, V oldValue, V newValue) {
            if (key == null || oldValue == null || newValue == null)
                throw new NullPointerException();
            return replaceNode(key, newValue, oldValue) != null;
        }
         */
    }
}
