package morning.cat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import morning.cat.server.handle.FirstServerHandler;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/9/25 10:57 AM
 */
public class NettyServer {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // 服务端引导类
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        /**
         * 1. 线程模型
         */
        serverBootstrap.group(bossGroup, workerGroup);

        /**
         * 2. IO模型
         */
        serverBootstrap.channel(NioServerSocketChannel.class);

        /**
         * 3. 连接读写处理逻辑
         */
        // 指定处理新连接数据的读写处理逻辑
        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel channel) {
                System.out.println("连接成功");

                // 指定连接数据读写逻辑
                channel.pipeline().addLast(new FirstServerHandler());
            }
        });

        // 指定在服务端启动过程中的一些逻辑
        serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
            @Override
            protected void initChannel(NioServerSocketChannel ch) {
                System.out.println("服务端启动中");
            }
        });

        // 给服务端channel设置一些属性
        // 表示系统用于临时存放已完成三次握手的请求的队列的最大长度，如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);

        /**
         * 4. 绑定端口启动
         */
//        serverBootstrap.bind(8000);
        bind(serverBootstrap, 8000);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
                bind(serverBootstrap, port + 1);
            }
        });
    }
}
