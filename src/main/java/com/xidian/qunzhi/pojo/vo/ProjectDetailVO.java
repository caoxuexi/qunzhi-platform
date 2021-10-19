package com.xidian.qunzhi.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Cao Study
 * @description <h1>ProjectDetailVO</h1>
 * @date 2021-10-19 11:27
 */
@Data
public class ProjectDetailVO {
    private Integer id;

    private String name;

    private String category;

    private String chatMethod;

    private String productKey;

    private String productSecret;

    /**
     * 1.低 2.正常 3.高
     */
    private Short powerDissipation;

    /**
     * 0.不共享 1.共享
     */
    private Short share;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;

}
