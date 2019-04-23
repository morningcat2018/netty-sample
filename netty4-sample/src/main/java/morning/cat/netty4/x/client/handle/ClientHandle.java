package morning.cat.netty4.x.client.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @describe: Netty4.x Handle
 * @author: morningcat.zhang
 * @date: 2019/4/22 10:37 PM
 */
public class ClientHandle extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }
}
