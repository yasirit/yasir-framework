package net.cn.yasir.framework.dto.enums;

/**
 * 方法枚举
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
public enum MethodEnum {
    //
    ADD("add", "新增"),
    MODIFY("modify", "修改"),
    DELETE("del", "删除"),
    SELECT("select", "查询")
    ;

    private String value;
    private String desc;

    MethodEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
