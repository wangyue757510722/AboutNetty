package discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by wangyue66 on 2017/12/4.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        /*ByteBuf in=(ByteBuf) msg;
        try {
            while(in.isReadable()){
                System.out.print((char) in.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg);

        }*/
        System.out.println(ctx.channel().remoteAddress()+"->Server :"+ msg.toString());
        ctx.write(msg);
        ctx.flush();

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
