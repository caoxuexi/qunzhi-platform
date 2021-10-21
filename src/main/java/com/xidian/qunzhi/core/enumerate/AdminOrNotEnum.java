package com.xidian.qunzhi.core.enumerate;

/**
 * @author Cao Study
 * @description <h1>AdminOrNotEnum</h1>
 * @date 2021-10-19 22:52
 */
public enum AdminOrNotEnum {
    NORMAL(0,"普通用户"),
    ADMIN(1, "管理员");

    private Integer value;

    public Integer getValue() {
        return this.value;
    }

    AdminOrNotEnum(Integer value, String description) {
        this.value =value;
    }

}
