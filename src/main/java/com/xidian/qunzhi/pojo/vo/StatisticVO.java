package com.xidian.qunzhi.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Column;

/**
 * @author Cao Study
 * @description <h1>StatisticVO</h1>
 * @date 2021-10-27 10:59
 */
@ApiModel(value = "返回给前端的统计数据VO",description = "管理员获取系统的统计数据时返回的VO")
@Data
public class StatisticVO {
    //总的用户数
    private Integer userCount;
    //项目总数
    private Integer projectCount;
}
