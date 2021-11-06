package com.xidian.qunzhi.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Cao Study
 * @description <h1>ServiceRecommendVO</h1>
 * @date 2021-11-06 9:58
 */
@Data
public class ServiceRecommendVO {
    private String funName;
    private List<String> microservice;
    private Integer number;
}
