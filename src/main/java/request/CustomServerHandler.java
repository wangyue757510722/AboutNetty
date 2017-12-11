package request;

/**
 * Created by wangyue66 on 2017/12/7.
 */
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

public class CustomServerHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        CustomMsg customMsg = (CustomMsg)msg;
        System.out.println("Client->Server:"+ctx.channel().remoteAddress()+" send "+customMsg.getBody());
        CustomMsg msg1 = new CustomMsg((byte)0xAB, (byte)0xCD, "received".length(), "received");
        //ctx.writeAndFlush(customMsg);
        ctx.writeAndFlush(msg1);
    }
    /*@Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        CustomMsg customMsg = (CustomMsg)msg;
        System.out.println("Client->Server:"+ctx.channel().remoteAddress()+" send "+customMsg.getBody());
        CustomMsg msg1 = new CustomMsg((byte)0xAB, (byte)0xCD, "received".length(), "received");
        //ctx.writeAndFlush(customMsg);
        ctx.writeAndFlush(msg1);
    }*/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }

}
