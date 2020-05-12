package net.cn.yasir.framework.tool.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.nio.charset.Charset;

/**
 * JSON序列化
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/3/25
 */
public class JsonSerializor {

    /**
     * 序列化
     * @param t
     * @param <T>
     * @return
     */
    public static <T> byte[] serial(T t) {
        if(t instanceof Serializable) {
            return JSON.toJSONString(t, true).getBytes(Charset.forName("UTF-8"));
        }
        throw new RuntimeException("Object must be implements Serializable");
    }

    /**
     * 反序列化
     * @param bytes
     * @param <T>
     * @return
     */
    public static <T> T unSerial(byte[] bytes) {
        return JSON.parseObject(new String(bytes, Charset.forName("UTF-8")), new TypeReference<T>(){});
    }
}
