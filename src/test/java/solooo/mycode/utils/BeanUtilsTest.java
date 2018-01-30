package solooo.mycode.utils;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Description:
 * Author:Eric
 * Date:2018/1/30
 */
public class BeanUtilsTest {

    @Test
    public void mainTest() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(ReadExcelUtils.class);
        System.out.println(beanInfo.getAdditionalBeanInfo());
        Enumeration<String> attributeNames = beanInfo.getBeanDescriptor().attributeNames();
        while (attributeNames.hasMoreElements()) {
            System.out.println(attributeNames.nextElement());
        }
        System.out.println(Arrays.toString(beanInfo.getPropertyDescriptors()));
    }
}