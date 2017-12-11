package send;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {


    private int counter;
	/*@Override
    public void channelActive( ChannelHandlerContext ctx) { // (1)
        final ByteBuf time = ctx.alloc().buffer(4); // (2)
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
        *//*f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                assert f == future;
                ctx.close();
            }
        }); *//*// (4)
    }*/
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        /*ByteBuf buf=(ByteBuf) msg;
        byte []req=new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body=new String(req,"UTF-8").substring(0,req.length-System.getProperty("line.separator").length());*/
        String body=(String )msg;
        System.out.println("the time receive order:"+ body +";the counter is: "+ ++counter);
        String currentTime="QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
        currentTime=currentTime+System.getProperty("line.separator");
        ByteBuf resp= Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);




    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
