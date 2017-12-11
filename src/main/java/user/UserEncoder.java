package user;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.nio.ByteBuffer;

/**
 * Created by wangyue66 on 2017/12/6.
 */
public class UserEncoder extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx,Object msg,ChannelPromise promise){
        User u=(User) msg;
        ByteBuf encode=ctx.alloc().buffer(4);
        encode.writeInt(u.getAge());
        ctx.write(encode,promise);
    }
}
