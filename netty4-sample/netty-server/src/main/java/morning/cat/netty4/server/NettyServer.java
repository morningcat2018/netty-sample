package morning.cat.netty4.server;

import io.netty.channel.ChannelInitializer;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/8 1:55 PM
 */
public interface NettyServer {

    void start(ChannelInitializer channelInitializer);
}
