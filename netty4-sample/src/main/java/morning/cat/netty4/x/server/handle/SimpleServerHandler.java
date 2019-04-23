package morning.cat.netty4.x.server.handle;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @describe: 简单处理
 * @author: morningcat.zhang
 * @date: 2019/4/23 2:42 PM
 */
public class SimpleServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            // Do something with msg
            if (msg instanceof ByteBuf) {
                ByteBuf byteBuf = (ByteBuf) msg;

//                while (byteBuf.isReadable()) {
//                    System.out.print((char) byteBuf.readByte());
//                    System.out.flush();
//                }

                System.out.println(byteBuf.toString(io.netty.util.CharsetUtil.US_ASCII));

            }


        } finally {
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
