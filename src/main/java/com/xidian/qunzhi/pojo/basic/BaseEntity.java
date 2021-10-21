package com.xidian.qunzhi.pojo.basic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @JsonIgnore
    @Column(name = "create_time",insertable=false, updatable=false)
    private Date createTime;

    @JsonIgnore
    @Column(name = "update_time",insertable=false, updatable=false)
    private Date updateTime;

    @JsonIgnore
    @Column(name = "delete_time")
    private Date deleteTime;
}
