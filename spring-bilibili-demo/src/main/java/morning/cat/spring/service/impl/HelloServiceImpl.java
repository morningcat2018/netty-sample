package morning.cat.spring.service.impl;

import morning.cat.spring.service.HelloService;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/17 3:28 PM
 * @Version 1.0
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public void hello() {
        System.out.println("HelloService hello method...");
    }
}
