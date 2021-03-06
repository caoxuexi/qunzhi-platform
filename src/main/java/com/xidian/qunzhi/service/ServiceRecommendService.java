package com.xidian.qunzhi.service;

import com.xidian.qunzhi.pojo.vo.ServiceRecommendVO;

import java.util.List;
import java.util.Map;

/**
 * @author Cao Study
 * @description <h1>ServiceRecommendService</h1>
 * @date 2021-11-06 9:39
 */
public interface ServiceRecommendService {
    List<ServiceRecommendVO> getService(List<String> functionNames);
    Map<String,String> getCodsUrl(String funcName);

}
