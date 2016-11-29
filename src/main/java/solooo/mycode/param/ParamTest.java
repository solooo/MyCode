package solooo.mycode.param;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:16/11/28-028
 * History:
 * his1:
 */
public class ParamTest {

    public void giveMeParam(String... ids) {
        System.out.println(ids[0]);
    }

    public static void main(String[] args) {
        ParamTest pt = new ParamTest();
        pt.giveMeParam("a", "b");

        String result = String.format("D%04d", 1);
        System.out.println(result);
    }
}
