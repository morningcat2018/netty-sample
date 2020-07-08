package morning.cat.netty4.server.handle;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @describe: DISCARD协议:忽略所有收到的数据
 * @author: morningcat.zhang
 * @date: 2019/4/23 2:42 PM
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

    //@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Discard the received data silently.
        ((ByteBuf) msg).release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}
