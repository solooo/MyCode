package solooo.mycode.ip;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:11/14-014
 * History:
 * his1:
 */
public class IpCheck {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Integer a1 = 100;
        Integer a2 = 100;
        System.out.println(a1 == a2); // true

        Integer b1 = 1000;
        Integer b2 = 1000;
        System.out.println(b1 == b2); // false

/*
        for (int i = 1; i <= 255; i++) {
            String ip = "192.168.1." + i;
            InetAddress n;
            try {
                n = InetAddress.getByName(ip);
                if (n.isReachable(10)) {
                    System.out.println(ip + " 在用");
                } else {
                    System.out.println(ip + " 为空");
                }
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ConnectException e) {
                // TODO Auto-generated catch block
                System.out.println("==============");
                System.out.println(ip);
                System.out.println("==============");
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }*/
    }
}
