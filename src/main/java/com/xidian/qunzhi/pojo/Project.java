package com.xidian.qunzhi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
public class Project {
    @Id
    private Integer id;

    private String name;

    private String category;

    @Column(name = "chat_method")
    private String chatMethod;

    @Column(name = "product_key")
    private String productKey;

    @Column(name = "product_secret")
    private String productSecret;

    /**
     * 1.低 2.正常 3.高
     */
    @Column(name = "power_dissipation")
    private Short powerDissipation;

    /**
     * 0.不共享 1.共享
     */
    private Short share;

    @Column(name = "current_user_count")
    private Integer currentUserCount;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @JsonIgnore
    @Column(name = "delete_time")
    private Date deleteTime;

}