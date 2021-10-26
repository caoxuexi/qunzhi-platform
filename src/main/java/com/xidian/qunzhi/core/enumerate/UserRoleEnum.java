package com.xidian.qunzhi.core.enumerate;

/**
 * @author Cao Study
 * @description <h1>UserRoleEnum</h1>
 * @date 2021-10-21 19:49
 */
public enum UserRoleEnum {
    LEADER(1, "组长"),
    MEMBER(2, "成员");

    private Integer value;
    private String description;

    public Integer getValue() {
        return this.value;
    }
    public String getDescription(){return this.description;};


    UserRoleEnum(Integer value, String description) {
        this.value = value;
        this.description=description;
    }
}
