package morning.cat.spring.ioc;

import morning.cat.spring.bean.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/9 6:08 PM
 * @Version 1.0
 */
public class SpringFrameworkTest3 {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MyConfig.class);
        applicationContext.refresh();

        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
        applicationContext.close();
    }
}

@Configuration
class MyConfig {

    @Bean
    public Person person() {
        Person person = new Person();
        person.setName("Annota");
        return person;
    }
}
