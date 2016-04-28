package solooo.mycode.netty.tcp;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/4/21
 * History:
 * his1:
 */
public class TcpServerManager {

    public static void main(String[] args) throws Exception {
        new TcpServer().start(5152); // 启动server
    }
}
