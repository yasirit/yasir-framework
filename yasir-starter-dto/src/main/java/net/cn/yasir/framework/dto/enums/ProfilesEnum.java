package net.cn.yasir.framework.dto.enums;

import com.google.common.collect.Lists;
import net.cn.yasir.framework.dto.base.EnumNode;

import java.util.*;

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

    private static final List<EnumNode<String, String>> enumNodeList = Lists.newArrayList();
    static {
        Enum[] enums = ProfilesEnum.values();
        for(Enum e : enums) {
            EnumNode<String, String> enumNode = new EnumNode<>(ProfilesEnum.valueOf(e.name()).getValue(), ProfilesEnum.valueOf(e.name()).getDesc());
            enumNodeList.add(enumNode);
        }
    }

    public static String getDescByValue(String value) {
        for(int i = 0; i < enumNodeList.size(); i++) {
            EnumNode<String, String> enumNode = enumNodeList.get(i);
            if(value.equals(enumNode.getValue())) {
                return enumNode.getDesc();
            }
        }
        return "";
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
