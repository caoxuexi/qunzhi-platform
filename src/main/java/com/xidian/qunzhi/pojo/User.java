package com.xidian.qunzhi.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
public class User extends BaseEntity{
    @Id
    private Integer id;

    private String nickname;

    private String password;

    private String profile;

    private String email;

    private String mobile;

    private String qq;

    private String address;

    private String district;

}