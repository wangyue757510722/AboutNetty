package user;

import com.sun.corba.se.internal.CosNaming.BootstrapServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * Created by wangyue66 on 2017/12/5.
 */
public class UserServer {
    private int port;
    public UserServer(int port){
        this.port=port;
    }

    public void run() throws Exception{
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup work=new NioEventLoopGroup();

        try {
            ServerBootstrap b=new ServerBootstrap();
            b.group(boss,work)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new UserEncoder());
                    ch.pipeline().addLast(new UserServerHandler());

                }
            })
            .option(ChannelOption.SO_BACKLOG, 128)
            .childOption(ChannelOption.SO_KEEPALIVE, true);


            ChannelFuture f=b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();

        }

    }

    public static void main(String[] args) throws Exception{
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new UserServer(port).run();
    }
}
