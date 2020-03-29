package net.cn.yasir.framework.tool.component;

import java.io.Serializable;

/**
 * 返回码
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
public class ReturnCode implements Serializable {

    private int code;

    private String msg;

    public ReturnCode() {
        super();
    }

    public ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
