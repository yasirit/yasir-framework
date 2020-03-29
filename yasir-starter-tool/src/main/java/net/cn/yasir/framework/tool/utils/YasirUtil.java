package net.cn.yasir.framework.tool.utils;

import net.cn.yasir.framework.tool.component.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @desc: Yasir
 * @author: yasir
 * @date: 2020/2/28 0:02
 */
public class YasirUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(YasirUtil.class);

    private YasirUtil() {}

    /**
     * 获取对象的字段名称集合
     * @param obj
     * @return
     */
    public static String[] getFiledNames(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for(int i = 0; i < fields.length; ++i) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 获取对象字段的值
     * @param fieldName
     * @param obj
     * @return
     */
    public static Object getFieldValue(String fieldName, Object obj) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = obj.getClass().getMethod(getter);
            return method.invoke(obj);
        } catch (Exception e) {
            LOGGER.error("取对象字段的值错误->{}", e.getMessage());
            return null;
        }
    }

    /**
     * 返回码格式化
     * @param source
     * @param str
     * @return
     */
    public static ReturnCode msgFormat(ReturnCode source, String... str) {
        source.setMsg(String.format(source.getMsg(), str));
        return source;
    }
}
