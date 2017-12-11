package user;

import io.netty.channel.*;

/**
 * Created by wangyue66 on 2017/12/5.
 */
public class UserServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(final ChannelHandlerContext ctx){
        ChannelFuture f=ctx.writeAndFlush(new User());
        f.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
