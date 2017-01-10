package solooo.mycode.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:17/1/9-009
 * History:
 * his1:
 */
public class DateUtils {

    public static void main(String args[]) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf.parse("2017-01-01 00:00:00");
        Date d2 = sdf.parse("2017-01-01 00:00:00");
        System.out.println(d1.after(d2));
        System.out.println(d1.before(d2));
        System.out.println(d1.compareTo(d2));
    }
}
