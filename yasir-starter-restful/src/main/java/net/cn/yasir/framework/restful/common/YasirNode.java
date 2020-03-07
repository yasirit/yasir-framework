package net.cn.yasir.framework.restful.common;

/**
 * 自定义节点泛型
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
public class YasirNode<K, V> {

    private K key;

    private V value;

    public YasirNode() {
        super();
    }

    public YasirNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
