package net.cn.yasir.framework.restful.config;

import net.cn.yasir.framework.dto.base.ExceptionCode;
import net.cn.yasir.framework.dto.base.YaSirException;
import net.cn.yasir.framework.dto.resp.WebJsonRespObj;
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
    public WebJsonRespObj YaSirExceptionHandler(YaSirException e) {
        LOGGER.error("拦截自定义异常：{}", e);
        return new WebJsonRespObj(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public WebJsonRespObj ErrorHandler(Exception e) {
        LOGGER.error("拦截系统异常：{}", e);
        return new WebJsonRespObj(ExceptionCode.SYSTEM_ERROR);
    }
}
