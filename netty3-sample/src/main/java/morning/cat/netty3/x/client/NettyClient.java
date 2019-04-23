package morning.cat.netty3.x.client;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @describe: Netty3.x Client
 * @author: morningcat.zhang
 * @date: 2019/4/22 10:37 PM
 */
public class NettyClient {

    public static void main(String[] args) {

        // 客户端
        ClientBootstrap bootstrap = new ClientBootstrap();

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService work = Executors.newCachedThreadPool();

        // 设置 ChannelFactory 工厂
        bootstrap.setFactory(new NioClientSocketChannelFactory(boss, work));

        // 设置管道工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {

                // 获取管道
                ChannelPipeline channelPipeline = Channels.pipeline();

                // Channel Pipeline

                // 接收信息转换成string(上行)
                channelPipeline.addLast("StringDecoder", new StringDecoder());
                // 回写直接写入字符串
                channelPipeline.addLast("StringEncoder", new StringEncoder());

                channelPipeline.addLast("ClientHandle", new ClientHandle());
                return channelPipeline;
            }
        });

        // 绑定端口
        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 59999));
        System.out.println("Netty Client start...");

        Channel channel = channelFuture.getChannel();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入：");
            channel.write(scanner.next());
        }

    }
}
