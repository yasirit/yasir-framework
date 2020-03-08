package net.cn.yasir.framework.restful.common;

/**
 * 自定义异常码
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
public class ExceptionCode {
    public static final ReturnCode SUCCESS = new ReturnCode(200, "SUCCESS");

    public static final ReturnCode SYSTEM_ERROR = new ReturnCode(500, "网络开小差了，请刷新后重试！");

    public static final ReturnCode TOKEN_EXPIRE = new ReturnCode(1000, "登录态已过期，请重新登录！");

    public static final ReturnCode TOKEN_VERIFY_ERROR = new ReturnCode(1001, "TOKEN验证异常，请重新登录！");
}
