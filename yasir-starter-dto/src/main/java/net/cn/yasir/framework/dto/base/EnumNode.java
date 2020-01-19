package net.cn.yasir.framework.dto.base;

import lombok.Data;

/**
 * 枚举节点泛型
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
@Data
public class EnumNode<V, D> {

    private V value;

    private D desc;

    public EnumNode() {
        super();
    }

    public EnumNode(V value, D desc) {
        this.value = value;
        this.desc = desc;
    }
}
