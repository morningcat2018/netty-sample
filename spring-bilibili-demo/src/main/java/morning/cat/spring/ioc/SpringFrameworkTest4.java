package morning.cat.spring.ioc;

import morning.cat.spring.bean.Person;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @describe: API 注入Bean
 * @author: morningcat.zhang
 * @date: 2020/7/16 11:35 AM
 * @Version 1.0
 */
public class SpringFrameworkTest4 {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Person.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 333L)
                .addPropertyValue("name", "小乔");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        applicationContext.registerBeanDefinition("person", beanDefinition);
        applicationContext.refresh();

        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
        applicationContext.close();
    }
}
