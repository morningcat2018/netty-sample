package morning.cat.netty4.server.dispatcher;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/8 3:40 PM
 * @Version 1.0
 */
public interface Dispatcher {
    HttpResponse dispatcher(HttpRequest httpRequest);
}
