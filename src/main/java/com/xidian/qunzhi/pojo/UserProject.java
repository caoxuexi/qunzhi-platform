package com.xidian.qunzhi.pojo;

import lombok.Data;

import javax.persistence.*;

@Table(name = "user_project")
@Data
public class UserProject extends BaseEntity{
    @Id
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "product_id")
    private Integer productId;

    /**
     * 用户对于项目的角色
     */
    @Column(name = "user_role")
    private Short userRole;
}