package send;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import javax.lang.model.type.TypeMirror;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private int counter;
    private byte[] req;
    public TimeClientHandler(){
        req=("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        ByteBuf message =null;
        System.out.println("here");
        for(int i=0;i<10;i++){
            message=Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }

    }
 	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*ByteBuf m = (ByteBuf) msg; // (1)
        try {
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(currentTimeMillis);
            System.out.println(new Date(currentTimeMillis));
            //ctx.close();
        } finally {
            m.release();
        }*/
        /*ByteBuf buf=(ByteBuf) msg;
        byte []req=new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body=new String(req,"UTF-8");*/
        String body=(String) msg;
        System.out.println("now is: "+body +" ; the counter is:" + ++counter);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
