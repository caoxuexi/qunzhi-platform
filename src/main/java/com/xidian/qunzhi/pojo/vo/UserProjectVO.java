package com.xidian.qunzhi.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author Cao Study
 * @description <h1>UserProjectVO</h1>
 * @date 2021-10-26 11:52
 */
@Data
public class UserProjectVO {
    private Integer userId;

    private String userNickname;

    private String userRealname;

    /**
     * 用户对于项目的角色
     */
    private String userRole;

}
