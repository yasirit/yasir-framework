package net.cn.yasir.framework.restful.common;

import java.io.Serializable;

/**
 * 自定义节点泛型
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
public class YasirNode<K, V> implements Serializable {

    private K key;

    private V value;

    public YasirNode() {
        super();
    }

    public YasirNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
