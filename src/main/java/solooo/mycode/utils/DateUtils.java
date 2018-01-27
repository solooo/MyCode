package solooo.mycode.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title 日期工具类
 * @Description
 * @Copyright Copyright 2016 HtDataCloud
 * @Author 殷建卫(yinjw@htdatacloud.com)
 * @Date 2016年6月27日
 * @History
 * @his1
 */
public class DateUtils {
    public static final String YYYY = "yyyy";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private DateUtils() {
    }

    /**
     * @param ms
     * @return
     * @Description 将毫秒数换算成*天，*时，*分，*秒
     * @Author 殷建卫(yinjw@htdatacloud.com)
     * @Date 2016年6月15日
     * @History
     * @his1
     */
    public static String milliSecondFormat(long ms) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        // 天
        if (ms >= dd) {
            return ms / dd + "天";
        }

        // 小时
        if (ms >= hh) {
            return ms / hh + "小时";
        }

        // 分钟
        if (ms >= mi) {
            return ms / mi + "分钟";
        }

        // 秒
        if (ms >= ss) {
            return ms / ss + "秒";
        }

        // 毫秒不显示
        if (ms < ss) {
            return "0秒";
        }
        return "";
    }

    /**
     * 将毫秒数换算成x天x时x分x秒x毫秒
     *
     * @param ms   毫秒
     * @param type 1.完整的时间 2.到时、分、秒 3.到时、分、秒、毫秒 默认为.到分、秒
     * @return 时间
     */
    public static String milliSecondFormat(long ms, String type) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : Long.toString(day);
        String strHour = hour < 10 ? "0" + hour : Long.toString(hour);
        String strMinute = minute < 10 ? "0" + minute : Long.toString(minute);
        String strSecond = second < 10 ? "0" + second : Long.toString(second);
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : Long.toString(milliSecond);
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : strMilliSecond;

        String time;
        switch (Integer.valueOf(type)) {
        case 1:
            time = strDay + "-" + strHour + ":" + strMinute + ":" + strSecond + "-" + strMilliSecond;
            break;
        case 2:
            time = strHour + ":" + strMinute + ":" + strSecond;
            break;
        case 3:
            time = strHour + ":" + strMinute + ":" + strSecond + " " + strMilliSecond;
            break;
        default:
            time = strMinute + ":" + strSecond;
        }
        return time;
    }

    /**
     * @param str
     * @return
     * @throws ParseException
     * @Description 字符串转时间
     * @Author 殷建卫(yinjw@htdatacloud.com)
     * @Date 2016年6月27日
     * @History
     * @his1
     */
    public static Date toDate(String str, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(str);
    }

    /**
     * Description：时间转字符串
     * Author:朱文华(zhuwh@htdatacloud.com)
     * Date:2016年10月19日
     * History:
     * his1:
     *
     * @param date
     * @param format
     * @return
     */
    public static String toString(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 判断target是否在start和end之间
     *
     * @param target
     * @param start
     * @param end
     * @return
     */
    public static boolean between(Date target, Date start, Date end) {
        Calendar starCal = Calendar.getInstance();
        starCal.setTime(start);
        starCal.set(Calendar.HOUR_OF_DAY, 0);
        starCal.set(Calendar.MINUTE, 0);
        starCal.set(Calendar.SECOND, 0);
        Date startTime = starCal.getTime();
        starCal.setTime(end);
        starCal.set(Calendar.HOUR_OF_DAY, 23);
        starCal.set(Calendar.MINUTE, 59);
        starCal.set(Calendar.SECOND, 59);
        Date endTime = starCal.getTime();

        return target.getTime() - startTime.getTime() >= 0 && target.getTime() - endTime.getTime() <= 0;
    }

    /**
     * @return
     * @Description 获取当年第一天
     * @Author 裴健(peij@htdatacloud.com)
     * @Date 2016年12月22日
     * @History
     * @his1
     */
    public static Date startOfYear() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return startOfYear(year);
    }

    /**
     * @return
     * @Description 指定年份第一天
     * @Author 裴健(peij@htdatacloud.com)
     * @Date 2016年12月22日
     * @History
     * @his1
     */
    public static Date startOfYear(Integer year) {
        if (year == null) {
            return startOfYear();
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        return cal.getTime();
    }

    /**
     * @return
     * @Description 获取当年最后一天
     * @Author 裴健(peij@htdatacloud.com)
     * @Date 2016年12月22日
     * @History
     * @his1
     */
    public static Date endOfYear() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return endOfYear(year);
    }

    /**
     * @return
     * @Description 指定年份最后一天
     * @Author 裴健(peij@htdatacloud.com)
     * @Date 2016年12月22日
     * @History
     * @his1
     */
    public static Date endOfYear(Integer year) {
        if (year == null) {
            return endOfYear();
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        return cal.getTime();
    }

    /**
     * @return
     * @Description 当月第一天
     * @Author 裴健(peij@htdatacloud.com)
     * @Date 2016年12月23日
     * @History
     * @his1
     */
    public static Date startOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * @return
     * @Description 当月最后一天
     * @Author 裴健(peij@htdatacloud.com)
     * @Date 2016年12月23日
     * @History
     * @his1
     */
    public static Date endOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.SECOND, -1);
        return cal.getTime();
    }

    /**
     * @return
     * @Description 当天开始时间
     * @Author 裴健(peij@htdatacloud.com)
     * @Date 2016年12月23日
     * @History
     * @his1
     */
    public static Date startOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * @return
     * @Description 当天结束时间
     * @Author 裴健(peij@htdatacloud.com)
     * @Date 2016年12月23日
     * @History
     * @his1
     */
    public static Date endOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * @param date
     * @return
     * @Description 判断是否为周末，周六和周日返回true
     * @Author 裴健(peij@htdatacloud.com)
     * @Date 2017年1月9日
     * @History
     * @his1
     */
    public static boolean isWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return week == 6 || week == 0;
    }

    public static void main(String[] args) throws ParseException {
//        System.out.println(DateUtils.milliSecondFormat(88000000L));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf.parse("2017-01-01 00:00:00");
        Date d2 = sdf.parse("2017-01-01 23:59:59");
        Date d3 = sdf.parse("2017-01-01 00:00:00");

        System.out.println(d1.after(d2));
        System.out.println(d1.before(d2));
        System.out.println(d1.equals(d2));
        System.out.println(d3.compareTo(d1) >= 0 && d3.compareTo(d2) <= 0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);

        System.out.println(cal.get(Calendar.DAY_OF_WEEK) - 1);
    }
}
