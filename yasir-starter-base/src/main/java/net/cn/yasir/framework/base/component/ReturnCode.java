package net.cn.yasir.framework.base.component;

import lombok.Data;

/**
 * 返回码
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
@Data
public class ReturnCode {

    public static final ReturnCode SUCCESS = new ReturnCode(200, "SUCCESS");

    private int code;

    private String msg;

    public ReturnCode() {
        super();
    }

    public ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
