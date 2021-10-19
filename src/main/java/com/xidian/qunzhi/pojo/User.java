package com.xidian.qunzhi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class User{
    @Id
    private Integer id;

    private String nickname;

    private String realname;

    private String password;

    private String profile;

    private String email;

    private String mobile;

    private String qq;

    private String address;

    private String district;

    @Column(name = "is_admin")
    private Integer isAdmin;


    @JsonIgnore
    @Column(insertable=false, updatable=false)
    private Date createTime;

    @JsonIgnore
    @Column(insertable=false, updatable=false)
    private Date updateTime;
}