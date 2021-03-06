package com.xidian.qunzhi.pojo.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "分页查询的基础DTO",description = "分页查询参数封装")
public class PageDTO {
    @Min(value = 0,message = "【页码】不能小于0")
    @NotNull(message = "【页码】不能为空")
    @ApiModelProperty(value = "页码",name = "page",example = "1")
    private int page;

    @NotNull(message = "【每页条数】不能为空")
    @Max(value = 100, message = "【每页条数】不能超过100")
    @ApiModelProperty(value = "每页数据条数",name = "size",example = "10")
    private int size;
}