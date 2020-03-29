package net.cn.yasir.framework.tool.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * 获取Http请求参数
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
public class RequestParamsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestParamsUtil.class);

    private RequestParamsUtil() {}

    /**
     * 获取GET请求参数
     *
     * @param req
     * @return
     */
    public static String getParams(HttpServletRequest req) {
        Map<String, String> params = Maps.newHashMap();
        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()) {
            String key = names.nextElement();
            params.put(key, req.getParameter(key));
        }
        return JSONObject.toJSONString(params);
    }

    /**
     * 获取POST请求参数
     *
     * @param req
     * @return
     */
    public static String postParams(HttpServletRequest req) {
        int len = req.getContentLength();
        byte[] buffer = new byte[len];
        ServletInputStream in = null;
        try {
            in = req.getInputStream();
            in.read(buffer, 0, len);
            in.close();
        } catch (IOException e) {
            LOGGER.error("获取POST请求参数错误：{}", e);
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOGGER.error("关闭流错误：{}", e);
                }
            }
        }
        return new String(buffer);
    }

}
