package morning.cat.netty3.x.handle;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

/**
 * @describe: Netty3.x Server Handle
 * @author: morningcat.zhang
 * @date: 2019/4/22 10:37 PM
 */
public class HelloHandle extends SimpleChannelHandler {
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        ChannelBuffer channelBuffer = (ChannelBuffer) e.getMessage();
        String message = new String(channelBuffer.array());
        System.out.println("接收消息：" + message);

        ChannelBuffer writeMsg = ChannelBuffers.copiedBuffer((" 已接收消息" + message).getBytes());
        ctx.getChannel().write(writeMsg);

        super.messageReceived(ctx, e);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("发生异常");
        //super.exceptionCaught(ctx, e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("新客户端打开连接" + ctx.getName());
        super.channelConnected(ctx, e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("关闭连接");
        super.channelDisconnected(ctx, e);
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("关闭通道");
        super.channelClosed(ctx, e);
    }
}
