package net.cn.yasir.framework.restful.common;

import java.util.UUID;

/**
 * 接口请求结果泛型
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
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
        this.code = ExceptionCode.SUCCESS.getCode();
        this.msg = ExceptionCode.SUCCESS.getMsg();
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
