package user;

import io.netty.channel.*;

import java.util.Objects;

/**
 * Created by wangyue66 on 2017/12/5.
 */
public class UserClientHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        User u=(User) msg;
        System.out.println(u);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();

    }
}
