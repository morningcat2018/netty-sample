package morning.cat.netty4.x;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @describe: Netty4.x Server
 * @author: morningcat.zhang
 * @date: 2019/4/23 12:13 AM
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        // 设置 NioSocket 工厂
        serverBootstrap.group(boss, work);
        serverBootstrap.channel(NioServerSocketChannel.class);

        // 设置管道工厂
        serverBootstrap.childHandler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel channel) throws Exception {

                ChannelPipeline channelPipeline = channel.pipeline();
                // 接收信息转换成string(上行)
                channelPipeline.addLast("StringDecoder", new StringDecoder());
                // 回写直接写入字符串
                channelPipeline.addLast("StringEncoder", new StringEncoder());
                channelPipeline.addLast("HelloHandle", new HelloHandle());
            }
        });

        ChannelFuture channelFuture = serverBootstrap.bind(9997);
        System.out.println("Netty4 Server start...");

        // telnet 127.0.0.1 9997
        channelFuture.channel().closeFuture().sync();
    }
}
