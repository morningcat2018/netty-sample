package morning.cat.netty4.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import morning.cat.netty4.client.channel.TestClientChannelInitializer;

/**
 * @describe: TODO 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/8 8:16 PM
 * @Version 1.0
 */
public class NettyClientMain {

    public static void main(String[] args) {

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 客户端
            Bootstrap clientBootstrap = new Bootstrap();
            // 设置 NioSocket 工厂
            clientBootstrap.group(workerGroup);
            clientBootstrap.channel(NioSocketChannel.class);
            // 设置管道工厂
            clientBootstrap.handler(new TestClientChannelInitializer());
            // 设置参数
            clientBootstrap.option(ChannelOption.SO_BACKLOG, 128);
            // 连接服务端
            ChannelFuture channelFuture = clientBootstrap.connect("127.0.0.1", 51001).sync();

            //

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            workerGroup.shutdownGracefully();
        }
    }
}
