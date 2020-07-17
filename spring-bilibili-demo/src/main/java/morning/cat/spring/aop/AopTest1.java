package morning.cat.spring.aop;

import morning.cat.spring.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/17 3:28 PM
 * @Version 1.0
 */
public class AopTest1 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");

        //HelloService helloService = beanFactory.getBean("helloService", HelloService.class);
        //helloService.hello();

        HelloService proxy = beanFactory.getBean("helloServiceProxy", HelloService.class);
        proxy.hello();
    }
}
