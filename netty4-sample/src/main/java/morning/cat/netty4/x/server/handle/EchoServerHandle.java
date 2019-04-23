package morning.cat.netty4.x.server.handle;

import io.netty.channel.*;

import java.net.SocketAddress;

/**
 * @describe: 类描述信息
 * @author: morningcat.zhang
 * @date: 2019/4/23 3:41 PM
 * @see ChannelOutboundHandlerAdapter,ChannelInboundHandlerAdapter
 */
public class EchoServerHandle extends ChannelOutboundHandlerAdapter {

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        System.out.println("新客户端连接");
        super.connect(ctx, remoteAddress, localAddress, promise);
    }

    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        System.out.println("客户端断开连接");
        super.disconnect(ctx, promise);
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        System.out.println("客户端关闭");
        super.close(ctx, promise);
    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        super.read(ctx);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {

        Channel channel = ctx.channel();
        channel.writeAndFlush("已收到此消息");

        super.write(ctx, msg, promise);
    }


}
