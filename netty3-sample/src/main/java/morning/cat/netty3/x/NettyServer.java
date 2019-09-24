package morning.cat.netty3.x;

import morning.cat.netty3.x.handle.HelloHandle;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @describe: Netty3.x Server
 * @author: morningcat.zhang
 * @date: 2019/4/22 10:37 PM
 */
public class NettyServer {

    public static void main(String[] args) {

        // 服务端
        ServerBootstrap bootstrap = new ServerBootstrap();

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService work = Executors.newCachedThreadPool();

        // 设置 ChannelFactory 工厂
        bootstrap.setFactory(new NioServerSocketChannelFactory(boss, work));

        // 设置 ChannelPipelineFactory 工厂
        bootstrap.setPipelineFactory(() -> {

                    // 生成管道
                    ChannelPipeline channelPipeline = Channels.pipeline();

                    // 接收信息转换成字符串(上行)
                    // channelPipeline.addLast("StringDecoder",new StringDecoder());
                    // 回写直接写入字符串
                    // channelPipeline.addLast("StringEncoder",new StringEncoder());
                    channelPipeline.addLast("HelloHandle", new HelloHandle());
                    return channelPipeline;
                }
        );

        // 绑定端口
        bootstrap.bind(new InetSocketAddress(59999));
        // telnet 127.0.0.1 59999

        System.out.println("Netty Server start...");

    }
}
