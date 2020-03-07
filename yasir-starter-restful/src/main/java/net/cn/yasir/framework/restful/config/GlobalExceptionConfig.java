package net.cn.yasir.framework.restful.config;

import net.cn.yasir.framework.restful.common.ExceptionCode;
import net.cn.yasir.framework.restful.common.ReturnCode;
import net.cn.yasir.framework.restful.common.YaSirException;
import net.cn.yasir.framework.restful.common.WebJsonObjectResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
@RestControllerAdvice
public class GlobalExceptionConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionConfig.class);

    @ExceptionHandler(value = YaSirException.class)
    public WebJsonObjectResp YaSirExceptionHandler(YaSirException e) {
        LOGGER.error("拦截自定义异常：{}", e);
        return new WebJsonObjectResp(new ReturnCode(e.getCode(), e.getMsg()));
    }

    @ExceptionHandler(value = Exception.class)
    public WebJsonObjectResp ErrorHandler(Exception e) {
        LOGGER.error("拦截系统异常：{}", e);
        return new WebJsonObjectResp(ExceptionCode.SYSTEM_ERROR);
    }
}
