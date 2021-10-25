package com.xidian.qunzhi.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author Cao Study
 * @description <h1>ProjectPreviewVO</h1>
 * @date 2021-10-18 16:24
 */
@ApiModel(value = "项目预览VO",description = "在开发中的个人项目页面展示")
@Data
public class ProjectPreviewVO {
    private Integer id;
    private String name;
    private String productKey;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;
}
