package morning.cat.spring;

import morning.cat.spring.bean.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/9 5:51 PM
 * @Version 1.0
 */
public class SpringFrameworkTest2 {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");

        Person person = beanFactory.getBean("person", Person.class);
        System.out.println(person);


    }
}
