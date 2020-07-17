package morning.cat.spring.aop.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/17 3:30 PM
 * @Version 1.0
 */
public class SimpleAdvisor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        System.out.println("通知器：调用之前");
        Object object = invocation.proceed();
        System.out.println("通知器：调用之后");
        return object;
    }
}
