package solooo.mycode.utils;

import org.apache.commons.lang3.StringUtils;
import solooo.mycode.exception.WebException;

/**
 * Description: 常用属性检查
 * Author:Eric
 * Date:2018/1/26
 */
public class HAssert {
    /**
     * 判断字符串是否为空
     * @param str 字符串
     * @param msg 为空提示信息
     */
    public static void isBlank(String str, String msg) {
        if (StringUtils.isBlank(str)) {
            throw new WebException(msg);
        }
    }

    /**
     * 判断对象是否为空
     * @param obj 对象
     * @param msg 为空提示信息
     */
    public static void isBlank(Object obj, String msg) {
        if (obj instanceof String) {
            isBlank((String) obj, msg);
        }
        if (obj == null) {
            throw new WebException(msg);
        }
    }

    /**
     * 如果条件为true, 异常提醒
     * @param b
     * @param msg
     */
    public static void isTrue(Boolean b, String msg) {
        if (b) {
            throw new WebException(msg);
        }
    }

    /**
     * 异常提醒
     * @param msg
     */
    public static void error(String msg) {
        throw new WebException(msg);
    }
}
