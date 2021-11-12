package com.xidian.qunzhi.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author Cao Study
 * @description <h1>ProjectDTO</h1>
 * @date 2021-10-21 14:25
 */
@ApiModel(value = "新建项目参数DTO",description = "新建项目时的参数封装")
@Data
public class ProjectDTO {
    @ApiModelProperty(value = "项目id(新建时无需填入)",name = "id",example = "6",required = false)
    private Integer id;

    @ApiModelProperty(value = "项目名称",name = "name",example = "智慧图书馆系统",required = true)
    @NotEmpty(message = "项目名称不能为空")
    private String name;

    @ApiModelProperty(value = "项目目录",name = "category",example = "管理系统/后台",required = true)
    @NotEmpty(message = "项目目录不能为空")
    private String category;

    @ApiModelProperty(value = "通讯方式",name = "chatMethod",example = "Http",required = false)
    private String chatMethod;

    /**
     * 1.低 2.正常 3.高
     */
    @ApiModelProperty(value = "耗能",name = "powerDissipation",example = "2",required = false)
    private Short powerDissipation;

    /**
     * 0.不共享 1.共享
     */
    @ApiModelProperty(value = "是否共享",name = "share",example = "1",required = false)
    private Short share;

    @ApiModelProperty(value = "功能要求",name = "funcNames",example = "[\"用户管理\" ]",required = false)
    private List<String> funcNames;
}
