package net.cn.yasir.framework.tool.component;

/**
 * 自定义异常码
 * 1001-1999 请求异常
 * 2000-2999 服务异常
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
public class ExceptionCode {
    public static final ReturnCode SUCCESS = new ReturnCode(200, "SUCCESS");

    public static final ReturnCode SYSTEM_ERROR = new ReturnCode(1000, "网络开小差了，请刷新后重试！");

    public static final ReturnCode TOKEN_EXPIRE = new ReturnCode(1001, "登录态已过期，请重新登录！");

    public static final ReturnCode TOKEN_VERIFY_ERROR = new ReturnCode(1002, "TOKEN验证异常，请重新登录！");

    public static final ReturnCode PARAM_ERROR = new ReturnCode(1003, "参数异常！");

    public static final ReturnCode HTTP_METHOD_ERROR = new ReturnCode(1004, "HTTP METHOD不匹配！");

    public static final ReturnCode PARAM_INVAILD = new ReturnCode(1005, "参数校验失败[%s]");

    public static final ReturnCode RECORD_EXIST = new ReturnCode(1006, "%d记录已存在");

    public static final ReturnCode RECORD_NOT_EXIST = new ReturnCode(1007, "%d记录不存在");

}
