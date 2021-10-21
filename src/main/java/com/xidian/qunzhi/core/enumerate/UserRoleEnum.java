package com.xidian.qunzhi.core.enumerate;

/**
 * @author Cao Study
 * @description <h1>UserRoleEnum</h1>
 * @date 2021-10-21 19:49
 */
public enum UserRoleEnum {
    LEADER(1, "项目组长"),
    MEMBER(2, "项目成员");

    private Integer value;

    public Integer getValue() {
        return this.value;
    }

    UserRoleEnum(Integer value, String description) {
        this.value = value;
    }
}
