package net.cn.yasir.framework.tool.component;

import cn.hutool.core.io.resource.ClassPathResource;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * @desc: 控制器 - 扩展
 * @author: yasir
 * @date: 2020/3/7 11:49
 */
public abstract class BaseResp implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseResp.class);

    protected  <T> WebJsonObjectResp<T> success() {
        return new WebJsonObjectResp(ExceptionCode.SUCCESS);
    }

    protected <T> WebJsonObjectResp<T> success(T data) {
        return new WebJsonObjectResp(ExceptionCode.SUCCESS, data);
    }

    protected <T> WebJsonObjectResp<T> success(String msg, T data) {
        return new WebJsonObjectResp(ExceptionCode.SUCCESS.getCode(), msg, data);
    }

    protected <T> WebJsonObjectResp<T> fail(int code, String msg) {
        return new WebJsonObjectResp(code, msg);
    }

    protected <T> WebJsonObjectResp<T> fail(ReturnCode returnCode) {
        return new WebJsonObjectResp(returnCode.getCode(), returnCode.getMsg());
    }

    protected <T> T getMockData(String filePath, Class<T> clazz) throws YaSirException {
        return JSON.parseObject(this.getFileContent(filePath), clazz);
    }

    protected <T> List<T> getMockListData(String filePath, Class<T> clazz) throws YaSirException {
        return JSON.parseArray(this.getFileContent(filePath), clazz);
    }

    private String getFileContent(String filePath) throws YaSirException {
        byte[] bytes;
        try {
            ClassPathResource resource = new ClassPathResource("/mock-data/" + filePath);
            bytes = resource.readBytes();
        } catch (Exception e) {
            throw new YaSirException(ExceptionCode.PARAM_ERROR);
        }
        return new String(bytes);
    }
}
