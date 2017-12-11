package user;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by wangyue66 on 2017/12/5.
 */
public class UserClient {

    public static void main(String[] args) throws Exception{
        String host="localhost";
        int port=8080;
        EventLoopGroup group=new NioEventLoopGroup();

        try {
            Bootstrap b=new Bootstrap();
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new UserDecoder());
                    ch.pipeline().addLast(new UserClientHandler());

                }
            });
            ChannelFuture f=b.connect(host,port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
