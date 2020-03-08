package net.cn.yasir.framework.restful.common;

import java.io.Serializable;

/**
 * 分页
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
public class BasePageReq implements Serializable {

    /**
     * 当前页
     * 默认为1
     */
    protected Integer pageNum = 1;

    /**
     * 每页展示条数
     * 默认20
     */
    protected Integer pageSize = 20;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
