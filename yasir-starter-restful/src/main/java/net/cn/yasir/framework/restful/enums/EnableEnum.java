package net.cn.yasir.framework.restful.enums;

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
