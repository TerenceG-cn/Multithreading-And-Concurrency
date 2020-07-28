package resultCache;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;

/**
 * 在因式分解中使用Memoizer来缓存结果
 */
public class Factorizer implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
    /**核心*/
    private final Computable<BigInteger,BigInteger[]> cache=new Computable<BigInteger, BigInteger[]>() {
        @Override
        public BigInteger[] compute(BigInteger arg) throws InterruptedException {
            return factor(arg);
        }
    };

    private BigInteger[] factor(BigInteger arg) {
        return new BigInteger[0];
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException
    {
        try{
            BigInteger i=extractFromRequest(servletRequest);
            encodeIntoResponse(servletResponse,cache.compute(i));
        }catch (InterruptedException e){
            encodeError(servletResponse,"factorization interrupted");
        }
    }
    /**核心*/
    private void encodeError(ServletResponse servletResponse, String factorization_interrupted) {
    }

    private void encodeIntoResponse(ServletResponse servletResponse, BigInteger[] compute) {
    }

    private BigInteger extractFromRequest(ServletRequest servletRequest) {
        return new BigInteger(servletRequest.toString());
    }
}
