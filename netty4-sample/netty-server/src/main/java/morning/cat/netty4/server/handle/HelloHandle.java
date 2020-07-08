package morning.cat.netty4.server.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @describe: Netty4.x Handle
 * @author: morningcat.zhang
 * @date: 2019/4/22 10:37 PM
 */
public class HelloHandle extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("收到消息：" + msg);

        ChannelPipeline channelPipeline = ctx.channel().pipeline();
        channelPipeline.write("ok");

    }
}
