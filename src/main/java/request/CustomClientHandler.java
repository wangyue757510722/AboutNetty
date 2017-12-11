package request;

/**
 * Created by wangyue66 on 2017/12/7.
 */
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class CustomClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        CustomMsg customMsg = new CustomMsg((byte)0xAB, (byte)0xCD, "Hello,Netty".length(), "Hello,Netty");
        ctx.writeAndFlush(customMsg);
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        CustomMsg customMsg=(CustomMsg) msg;
        System.out.println("Server->Client:" + ctx.channel().remoteAddress() + " send " + customMsg.getBody());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }

}
