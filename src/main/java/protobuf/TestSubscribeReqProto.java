package protobuf;


import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyue66 on 2017/12/11.
 */
public class TestSubscribeReqProto {
    private static byte[] encode(SubscribeReqProto.SubscribeReq req){
        return req.toByteArray();
    }
    private static SubscribeReqProto.SubscribeReq decode (byte[] body) throws InvalidProtocolBufferException{
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq(){
        SubscribeReqProto.SubscribeReq.Builder builder= SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("jack");
        builder.setProductName("netty book");
        List<String> address=new ArrayList<String>();
        address.add("beijing");
        address.add("shanghai");
        builder.addAllAddress(address);
        return  builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException{
        SubscribeReqProto.SubscribeReq req=createSubscribeReq();
        System.out.println("before: "+req.toString());
        SubscribeReqProto.SubscribeReq req2=decode(encode(req));
        System.out.println("after: "+req2.toString());

    }
}
