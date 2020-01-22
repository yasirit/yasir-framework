package net.cn.yasir.framework.dto.resp;

import net.cn.yasir.framework.dto.base.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础返回处理
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/22
 */
public class BaseResp {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseResp.class);

    public <T> WebJsonObjectResp<T> success() {
//        return WebJsonObjectResp.builder()
//                .code(ReturnCode.SUCCESS.getCode())
//                .msg(ReturnCode.SUCCESS.getMsg())
//                .build();
        return new WebJsonObjectResp(ReturnCode.SUCCESS);
    }

    public <T> WebJsonObjectResp<T> success(T data) {
        return new WebJsonObjectResp(ReturnCode.SUCCESS, data);
    }

    public <T> WebJsonObjectResp<T> success(String msg, T data) {
        return new WebJsonObjectResp(ReturnCode.SUCCESS.getCode(), msg, data);
    }

    public <T> WebJsonObjectResp<T> fail(int code, String msg) {
        return new WebJsonObjectResp(code, msg);
    }

    public <T> WebJsonObjectResp<T> fail(ReturnCode returnCode) {
        return new WebJsonObjectResp(returnCode.getCode(), returnCode.getMsg());
    }

}
