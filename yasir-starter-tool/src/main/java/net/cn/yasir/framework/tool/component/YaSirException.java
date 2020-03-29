package net.cn.yasir.framework.tool.component;

/**
 * 自定义异常类
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
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
