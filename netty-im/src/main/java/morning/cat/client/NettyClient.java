package morning.cat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import morning.cat.client.handle.FirstClientHandler;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/9/25 3:11 PM
 */
public class NettyClient {

    public static final Integer MAX_RETRY = 3;

    public static void main(String[] args) {


        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // 客户端引导类
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 1.指定线程模型
                .group(workerGroup)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // option() 方法可以给连接设置一些 TCP 底层相关的属性
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                // 3.IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        // 指定连接数据读写逻辑
                        ChannelPipeline channelPipeline = ch.pipeline();
                        channelPipeline.addLast(new FirstClientHandler());
                    }
                });

        // 4.建立连接
        connect(bootstrap, "localhost", 8000, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else {
                System.err.println("连接失败，开始重连");
                connect(bootstrap, host, port);
            }
        });
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        ChannelFuture channelFuture = bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else if (retry < 1) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");

                if (retry == 1) {
                    System.err.println("重试次数已用完，放弃连接！");
                    return;
                }
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });

    }
}
