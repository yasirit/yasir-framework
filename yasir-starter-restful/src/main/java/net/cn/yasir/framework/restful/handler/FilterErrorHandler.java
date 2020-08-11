package net.cn.yasir.framework.restful.handler;

import com.alibaba.fastjson.JSON;
import net.cn.yasir.framework.tool.component.ReturnCode;
import net.cn.yasir.framework.tool.component.WebJsonObjectResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 过滤器错误处理器
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/8/11
 */
public class FilterErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(FilterErrorHandler.class);

    /**
     * 错误处理
     * @param response
     * @param returnCode
     */
    protected void execute(ServletResponse response, ReturnCode returnCode) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            WebJsonObjectResp resp = new WebJsonObjectResp(returnCode.getCode(), returnCode.getMsg());
            writer.print(JSON.toJSONString(resp));
        } catch (IOException e) {
            log.info("过滤器错误处理异常，e:{}", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}
