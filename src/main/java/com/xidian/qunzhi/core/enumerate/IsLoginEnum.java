package com.xidian.qunzhi.core.enumerate;

/**
 * @author Cao Study
 * @description <h1>IsLoginEnum</h1>
 * @date 2021-10-25 20:06
 */
public enum IsLoginEnum {
    ALREADY_LOGIN(0,"普通用户"),
    NOT_LOGIN(1,"管理员");

    private Integer value;

    public Integer getValue() {
        return this.value;
    }

    IsLoginEnum(Integer value, String description) {
        this.value =value;
    }
}
