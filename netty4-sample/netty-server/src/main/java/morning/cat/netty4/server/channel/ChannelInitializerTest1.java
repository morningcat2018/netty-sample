package morning.cat.netty4.server.channel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpServerCodec;
import morning.cat.netty4.server.handle.TestHttpServerHandle;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/8 2:01 PM
 */
public class ChannelInitializerTest1 extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline channelPipeline = channel.pipeline();
        channelPipeline.addLast("httpServerCodec", new HttpServerCodec());
        channelPipeline.addLast("channelHandler", new TestHttpServerHandle());
    }
}
