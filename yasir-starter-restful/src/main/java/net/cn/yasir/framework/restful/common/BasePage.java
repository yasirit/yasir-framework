package net.cn.yasir.framework.restful.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
@Data
public class BasePage implements Serializable {

    /**
     * 当前页
     * 默认为1
     */
    protected Integer current = 1;

    /**
     * 每页展示条数
     * 默认20
     */
    protected Integer size = 20;
}
