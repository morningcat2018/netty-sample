package morning.cat.netty4.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2020/7/8 1:56 PM
 */
public class NettyServerImpl implements NettyServer {
    public void start(ChannelInitializer channelInitializer) {
        // 接收请求线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 工作线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // Netty 服务端
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 设置 NioSocket 工厂
            serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            // 设置管道工厂 ChannelHandler
            serverBootstrap.childHandler(channelInitializer);
            // 设置参数
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(51001).sync();
            System.out.println("Netty4 Server start...");

            channelFuture.channel().closeFuture().sync();
            System.out.println("closeFuture");
        } catch (InterruptedException e) {
            // logger
        } finally {
            // 优雅关闭
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
