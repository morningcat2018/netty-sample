package morning.cat.netty4.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import morning.cat.netty4.server.dispatcher.DefaultDispatcher;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/8 2:04 PM
 */
public class TestHttpServerHandle extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if (httpObject instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) httpObject;
            channelHandlerContext.writeAndFlush(new DefaultDispatcher().dispatcher(httpRequest));
        }
    }
}
