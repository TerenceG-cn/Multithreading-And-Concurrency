package resultCache;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger>{
    public BigInteger compute(String arg){
        //经过长时间计算后
        return new BigInteger(arg);
    }
}