package net.cn.yasir.framework.dto.resp;

import lombok.Data;
import net.cn.yasir.framework.dto.base.ReturnCode;

import java.util.UUID;

/**
 * 接口请求结果泛型
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
@Data
public class WebJsonObjectResp<T> {

    /**
     * uuid
     */
    private String uuid = UUID.randomUUID().toString();

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public WebJsonObjectResp(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public WebJsonObjectResp(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public WebJsonObjectResp(T data) {
        this.code = ReturnCode.SUCCESS.getCode();
        this.msg = ReturnCode.SUCCESS.getMsg();
        this.data = data;
    }

    public WebJsonObjectResp(ReturnCode returnCode) {
        this.code = returnCode.getCode();
        this.msg = returnCode.getMsg();
    }

    public WebJsonObjectResp(ReturnCode returnCode, T data) {
        this.code = returnCode.getCode();
        this.msg = returnCode.getMsg();
        this.data = data;
    }

    public static WebJsonObjectResp.WebJsonObjectRespBuilder builder() {
        return new WebJsonObjectResp.WebJsonObjectRespBuilder();
    }

    @Data
    public static class WebJsonObjectRespBuilder<T> {
        private int code;

        private String msg;

        private T data;

        WebJsonObjectRespBuilder() {}

        public WebJsonObjectResp.WebJsonObjectRespBuilder code(int code) {
            this.code = code;
            return this;
        }

        public WebJsonObjectResp.WebJsonObjectRespBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public WebJsonObjectResp.WebJsonObjectRespBuilder data(T data) {
            this.data = data;
            return this;
        }

        public WebJsonObjectResp build() {
            return new WebJsonObjectResp(this.code, this.msg, this.data);
        }
    }

}
