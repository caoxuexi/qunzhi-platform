package com.xidian.qunzhi.pojo;

import lombok.Data;

import javax.persistence.*;

@Table(name = "project_extra")
@Data
public class ProjectExtra {
    @Id
    private Integer id;

    @Column(name = "project_id")
    private Integer projectId;

    private String functions;

}