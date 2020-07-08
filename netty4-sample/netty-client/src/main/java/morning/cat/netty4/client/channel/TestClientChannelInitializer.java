package morning.cat.netty4.client.channel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/8 8:19 PM
 * @Version 1.0
 */
public class TestClientChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline channelPipeline = channel.pipeline();
        //channelPipeline.addLast("ClientHandle", new EchoClientHandler());
    }
}
