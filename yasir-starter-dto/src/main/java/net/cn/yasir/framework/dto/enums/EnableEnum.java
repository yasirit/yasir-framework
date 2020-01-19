package net.cn.yasir.framework.dto.enums;

import com.google.common.collect.Lists;
import net.cn.yasir.framework.dto.base.EnumNode;

import java.util.List;

/**
 * 是否枚举
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
public enum EnableEnum {
    //
    ENABLE(1, "是"),
    DISABLE(0, "否"),
    ;

    private int value;
    private String desc;

    EnableEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private static final List<EnumNode<Integer, String>> enumNodeList = Lists.newArrayList();
    static {
        Enum[] enums = EnableEnum.values();
        for(Enum e : enums) {
            EnumNode<Integer, String> enumNode = new EnumNode<>(EnableEnum.valueOf(e.name()).getValue(), EnableEnum.valueOf(e.name()).getDesc());
            enumNodeList.add(enumNode);
        }
    }

    public static String getDescByValue(String value) {
        for(int i = 0; i < enumNodeList.size(); i++) {
            EnumNode<Integer, String> enumNode = enumNodeList.get(i);
            if(value.equals(enumNode.getValue())) {
                return enumNode.getDesc();
            }
        }
        return "";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
