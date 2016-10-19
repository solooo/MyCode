package solooo.mycode.ssh;

import com.jcraft.jsch.Session;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:10/18-0018
 * History:
 * his1:
 */
public class SshTest {
    public static void main(String[] args) {
        Session session = null;
        try {
            session = JSSHUtil.connect("192.168.2.5", "root", "Htcl@ud@ab1");
            String result = JSSHUtil.execCmdExt(session, "sqoop import --connect jdbc:mysql://192.168.1.41:3306/resident --username root --password 123456 --table t_resident --fields-terminated-by ',' --target-dir /tmp/test -m 10");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.disconnect();
        }
    }
}
