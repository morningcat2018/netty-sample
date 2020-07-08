package morning.cat.netty4.server.controller;

import morning.cat.netty4.server.annotation.NettyMapper;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/8 3:59 PM
 * @Version 1.0
 */
@NettyMapper("/hello")
public class HelloController {

    @NettyMapper("/str")
    public String hello(String name, String age) {
        return "自强不息" + name + age;
    }

}
