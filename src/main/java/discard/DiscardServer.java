package discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import sun.reflect.generics.scope.DummyScope;



/**
 * Created by wangyue66 on 2017/12/4.
 */
public class DiscardServer {
    private int port;
    public DiscardServer(int port){
        this.port=port;
    }

    public void run() throws Exception{
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup work=new NioEventLoopGroup();

        try {
            ServerBootstrap b=new ServerBootstrap();
            b.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                            ch.pipeline().addLast("decoder", new StringDecoder());
                            ch.pipeline().addLast("encoder", new StringEncoder());
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture f=b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();

        }

    }

    public static void main(String[] args) throws Exception{
        int port;
        if(args.length>0){
            port=Integer.valueOf(args[0]);
        }else{
            port=8080;
        }
        new DiscardServer(port).run();
    }

}
