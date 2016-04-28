package solooo.mycode.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/4/21
 * History:
 * his1:
 */
public class TcpServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        ByteBuf bb = (ByteBuf) msg;
        byte[] b = new byte[bb.readableBytes()];
        bb.readBytes(b);
        System.out.println(new String(b, "utf-8"));
        bb.release();
        // 模拟响应
        String response ="{" +
                        "  \"aid\":\"\"," +
                        "  \"cmd\":\"upload\"," +
                        "  \"data\":{" +
                        "    \"seq\":1," +
                        "    \"oid\":\"oid\"," +
                        "    \"other\":{" +
                        "      \"gps\":[]," +
                        "      \"fileId\":\"xxx\"" +
                        "    }" +
                        "  }," +
                        "  \"error\":\"\"," +
                        "  \"len\":0," +
                        "  \"type\":\"resp\"," +
                        "  \"v\":\"0.1.0\"" +
                        "}";

        response = response.replaceAll("\\s+", "");
        // 在当前场景下，发送的数据必须转换成ByteBuf数组
        ByteBuf encoded = ctx.alloc().buffer(response.length());
        encoded.writeBytes(response.getBytes());
        ctx.write(encoded);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
