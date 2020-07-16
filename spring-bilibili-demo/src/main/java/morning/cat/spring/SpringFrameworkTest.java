package morning.cat.spring;

import morning.cat.spring.bean.Person;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/9 2:25 PM
 * @Version 1.0
 */
public class SpringFrameworkTest {

    public static void main(String[] args) {

        Resource resource = new ClassPathResource("applicationContext.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);

        Person person = beanFactory.getBean("person", Person.class);
        System.out.println(person);








    }
}
