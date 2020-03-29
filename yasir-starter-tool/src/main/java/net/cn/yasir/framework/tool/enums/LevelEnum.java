package net.cn.yasir.framework.tool.enums;

/**
 * 层级枚举
 *
 * @author 沈益鑫
 * @version 1.0.0
 * @date 2020/1/16
 */
public enum LevelEnum {
    //
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NIGHT(9),
    TEN(10)
    ;

    private int value;

    LevelEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
