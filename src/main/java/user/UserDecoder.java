package user;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.Objects;

/**
 * Created by wangyue66 on 2017/12/5.
 */
public class UserDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext ctx,ByteBuf in,List<Object> out){
        if (in.readableBytes() < 4) {
            return;
        }
        out.add(new User(in.readInt()));
    }

}
