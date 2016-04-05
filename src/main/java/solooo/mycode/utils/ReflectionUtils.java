package solooo.mycode.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

import org.springframework.util.Assert;

/**
 * 
 * Title:反射工具类
 * Description:反射工具类
 * Copyright:Copyright 2006 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016年4月5日
 * History:
 * his1:
 */
public class ReflectionUtils {
    public static final String CGLIB_CLASS_SEPARATOR = "$$";

    public static Object getFieldValue(Object object, String fieldName) {
        return getFieldValue(object, fieldName, true);
    }

    public static void setFieldValue(Object object, String fieldName, Object newValue) {
        try {
            setFieldValue(object, fieldName, newValue, true);
        } catch (NoSuchFieldException localNoSuchFieldException) {
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Object invokeMethod(Object object, String methodName, Object[] params)
                    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invokeMethod(object, methodName, true, params);
    }

    private static void setFieldValue(Object object, String propertyName, Object newValue,
                    boolean targetAccessible) throws NoSuchFieldException, IllegalAccessException {
        Assert.notNull(object);
        Assert.hasText(propertyName);

        Field field = getDeclaredField(object, propertyName);

        boolean accessible = field.isAccessible();
        field.setAccessible(targetAccessible);

        field.set(object, newValue);

        field.setAccessible(accessible);
    }

    private static Object getFieldValue(Object object, String fieldName, boolean targetAccessible) {
        Assert.notNull(object);
        Assert.hasText(fieldName);
        try {
            Field field = getDeclaredField(object, fieldName);
            boolean accessible = field.isAccessible();
            field.setAccessible(targetAccessible);

            Object result = field.get(object);

            field.setAccessible(accessible);

            return result;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Object invokeMethod(Object object, String methodName, boolean targetAccessible,
                    Object[] params) throws NoSuchMethodException, IllegalAccessException,
                    InvocationTargetException {
        Assert.notNull(object);
        Assert.hasText(methodName);

        Class[] types = new Class[params.length];

        for (int i = 0; i < params.length; ++i) {
            types[i] = params[i].getClass();
        }

        Class clazz = object.getClass();
        Method method = null;

        Class superClass = clazz;
        try {
            method = superClass.getDeclaredMethod(methodName, types);
        } catch (NoSuchMethodException localNoSuchMethodException) {
            do
                superClass = superClass.getSuperclass();
            while (superClass != Object.class);
        }

        if (method == null) {
            throw new NoSuchMethodException("No Such Method : " + clazz.getSimpleName() + "." + methodName
                            + Arrays.asList(types));
        }

        boolean accessible = method.isAccessible();
        method.setAccessible(targetAccessible);

        Object result = method.invoke(object, params);

        method.setAccessible(accessible);

        return result;
    }

    public static Field getDeclaredField(Object target, String fieldName) {
        Assert.notNull(target, "target不能为空");
        return getDeclaredField(getTargetClass(target), fieldName);
    }

    public static Field getDeclaredField(Class targetClass, String fieldName) {
        Assert.notNull(targetClass, "targetClass不能为空");
        Assert.hasText(fieldName, "fieldName不能为空");
        Class superClass = targetClass;
        try {
            Field field = superClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException field) {
            do
                superClass = superClass.getSuperclass();
            while (superClass != Object.class);
        }

        return null;
    }

    public static Class getSuperClassGenericType(Class clazz) {
        return getSuperClassGenericType(clazz, 0);
    }

    public static <T> Class<T> getSuperClassGenericType(Class<?> clazz, int index) {
        Type genType = clazz.getGenericSuperclass();

        if (clazz.getSimpleName().indexOf("$$EnhancerByCGLIB$$") != -1) {
            genType = ((Class) genType).getGenericSuperclass();
        }

        if (!(genType instanceof ParameterizedType)) {
            return null;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if ((index >= params.length) || (index < 0)) {
            return null;
        }

        if (!(params[index] instanceof Class)) {
            return null;
        }

        return ((Class) params[index]);
    }

    public static Class<?> getTargetClass(Object target) {
        Assert.notNull(target, "target不能为空");
        return getTargetClass(target.getClass());
    }

    public static Class<?> getTargetClass(Class<?> targetClass) {
        Assert.notNull(targetClass, "targetClass不能为空");

        Class clazz = targetClass;
        if ((clazz != null) && (clazz.getName().contains("$$"))) {
            Class superClass = clazz.getSuperclass();
            if ((superClass != null) && (!(Object.class.equals(superClass))))
                return superClass;
        }

        return clazz;
    }

    public static boolean hasDeclaredField(Class<?> clazz, String fieldName) {
        Field[] arrayOfField;
        int j = (arrayOfField = clazz.getDeclaredFields()).length;
        for (int i = 0; i < j; ++i) {
            Field field = arrayOfField[i];
            if (field.getName().equals(fieldName))
                return true;
        }

        return false;
    }
}
