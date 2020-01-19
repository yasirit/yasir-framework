package net.cn.yasir.framework.dto.resp;

import lombok.Data;
import net.cn.yasir.framework.dto.base.ExceptionCode;
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
public class WebJsonRespObj<T> {

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
     * 返回值说明
     */
    private String desc;

    /**
     * 返回数据
     */
    private T data;

    public WebJsonRespObj() {
        super();
    }

    public WebJsonRespObj(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public WebJsonRespObj(T data) {
        this.code = ExceptionCode.SUCCESS.getCode();
        this.msg = ExceptionCode.SUCCESS.getMsg();
        this.data = data;
    }

    public WebJsonRespObj(ReturnCode returnCode) {
        this.code = returnCode.getCode();
        this.msg = returnCode.getMsg();
    }

    public WebJsonRespObj(ReturnCode returnCode, T data) {
        this.code = returnCode.getCode();
        this.msg = returnCode.getMsg();
        this.data = data;
    }

}
