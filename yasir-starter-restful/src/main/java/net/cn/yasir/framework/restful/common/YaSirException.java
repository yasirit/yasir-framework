package net.cn.yasir.framework.restful.common;

import lombok.Data;

/**
 * 自定义异常类
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
@Data
public class YaSirException extends RuntimeException {

    private int code;

    private String msg;

    public YaSirException() {
        super();
    }

    public YaSirException(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public YaSirException(ReturnCode returnCode) {
        super();
        this.code = returnCode.getCode();
        this.msg = returnCode.getMsg();
    }

}
