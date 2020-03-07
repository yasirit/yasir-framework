package net.cn.yasir.framework.restful.enums;

/**
 * Profiles枚举
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
public enum ProfilesEnum {
    //
    DEV("dev", "开发环境"),
    SIT("sit", "测试环境"),
    UAT("uat", "预发布环境"),
    PROD("prod", "生产环境")
    ;

    private String value;
    private String desc;

    ProfilesEnum(String value, String desc) {
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
