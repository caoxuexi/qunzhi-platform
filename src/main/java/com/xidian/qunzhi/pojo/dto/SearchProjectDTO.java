package com.xidian.qunzhi.pojo.dto;

import com.xidian.qunzhi.pojo.basic.PageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Cao Study
 * @description <h1>SearchProjectDTO 管理员按条件查询project</h1>
 * @date 2021-10-20 20:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "管理员分页搜索参数DTO",description = "管理员分页搜索项目的参数封装")
public class SearchProjectDTO extends PageDTO {
    @ApiModelProperty(value = "项目名称",name = "name",example = "智慧",required = false)
    private String name;
}
