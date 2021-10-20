package com.xidian.qunzhi.pojo.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Cao Study
 * @description <h1>ProjectAdminVO</h1>
 * @date 2021-10-20 17:19
 */
@Data
public class ProjectAdminVO {
    private Integer id;

    private String name;

    private String category;

    private Integer currentUserCount;

    private Date createTime;

    private Date updateTime;
}
