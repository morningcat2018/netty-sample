package morning.cat.netty4.server;

import morning.cat.netty4.server.channel.ChannelInitializerTest1;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/8 2:06 PM
 */
public class NettyMain {

    /**
     * curl "http://localhost:51001"
     */
    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServerImpl();
        nettyServer.start(new ChannelInitializerTest1());
    }
}
