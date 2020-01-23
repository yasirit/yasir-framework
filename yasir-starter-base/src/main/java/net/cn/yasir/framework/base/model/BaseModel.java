package net.cn.yasir.framework.base.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 基础功能
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/17
 */
public class BaseModel implements Serializable {

    /**
     * 对象转Map
     * @return
     */
    public Map<String, String> toMap() {
        String[] fieldNames = this.getFiledNames(this);
        HashMap<String, String> map = new HashMap(fieldNames.length);
        for(int i = 0; i < fieldNames.length; ++i) {
            String name = fieldNames[i];
            String value = (String)this.getFieldValueByName(name, this);
            if (StringUtils.isNotEmpty(value)) {
                map.put(name, value);
            }
        }
        return map;
    }

    /**
     * 获取对象的字段名称
     * @param obj
     * @return
     */
    public String[] getFiledNames(Object obj) {
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
    public Object getFieldValueByName(String fieldName, Object obj) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = obj.getClass().getMethod(getter);
            return method.invoke(obj);
        } catch (Exception var6) {
            return null;
        }
    }

}
