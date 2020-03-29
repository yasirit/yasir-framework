package net.cn.yasir.framework.restful.config;

import net.cn.yasir.framework.tool.component.ExceptionCode;
import net.cn.yasir.framework.tool.component.ReturnCode;
import net.cn.yasir.framework.tool.component.WebJsonObjectResp;
import net.cn.yasir.framework.tool.component.YaSirException;
import net.cn.yasir.framework.tool.utils.YasirUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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
    public WebJsonObjectResp yaSirExceptionHandler(YaSirException e) {
        LOGGER.error("拦截自定义异常：{}", e);
        return new WebJsonObjectResp(new ReturnCode(e.getCode(), e.getMsg()));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public WebJsonObjectResp httpMethodExceptionHandler(HttpRequestMethodNotSupportedException e) {
        LOGGER.error("拦截HTTP METHOD异常：{}", e);
        return new WebJsonObjectResp(ExceptionCode.HTTP_METHOD_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public WebJsonObjectResp postArgNotValidExceptionHandler(MethodArgumentNotValidException e) {
        LOGGER.error("拦截HTTP PARAM INVAILD异常：{}", e);
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuilder str = new StringBuilder();
        allErrors.stream().forEach(objectError -> {
            FieldError fieldError = (FieldError)objectError;
            str.append(fieldError.getDefaultMessage());
        });
        return new WebJsonObjectResp(YasirUtil.msgFormat(ExceptionCode.PARAM_INVAILD, str.toString()));
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public WebJsonObjectResp getArgNotValidExceptionHandler(MissingServletRequestParameterException e) {
        LOGGER.error("拦截HTTP PARAM INVAILD异常：{}", e);
        return new WebJsonObjectResp(YasirUtil.msgFormat(ExceptionCode.PARAM_INVAILD, e.getMessage()));
    }


    @ExceptionHandler(value = Exception.class)
    public WebJsonObjectResp errorHandler(Exception e) {
        LOGGER.error("拦截系统异常：{}", e);
        return new WebJsonObjectResp(ExceptionCode.SYSTEM_ERROR);
    }
}
