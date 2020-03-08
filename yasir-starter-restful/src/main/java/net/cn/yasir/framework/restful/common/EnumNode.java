package net.cn.yasir.framework.restful.common;

import java.io.Serializable;

/**
 * 枚举节点泛型
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
public class EnumNode<V, D> implements Serializable {

    private V value;

    private D desc;

    public EnumNode() {
        super();
    }

    public EnumNode(V value, D desc) {
        this.value = value;
        this.desc = desc;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public D getDesc() {
        return desc;
    }

    public void setDesc(D desc) {
        this.desc = desc;
    }
}
