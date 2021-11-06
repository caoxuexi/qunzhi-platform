package com.xidian.qunzhi.service.impl;

import com.xidian.qunzhi.mapper.ServiceRecommendMapper;
import com.xidian.qunzhi.pojo.ServiceRecommend;
import com.xidian.qunzhi.pojo.vo.ServiceRecommendVO;
import com.xidian.qunzhi.service.ServiceRecommendService;
import com.xidian.qunzhi.utils.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cao Study
 * @description <h1>ServiceRecommendServiceImpl</h1>
 * @date 2021-11-06 9:39
 */
@Service
public class ServiceRecommendServiceImpl implements ServiceRecommendService {
    @Autowired
    private ServiceRecommendMapper serviceRecommendMapper;

    @Override
    public Map<String, List<ServiceRecommendVO>> getService( List<String> functionNames) {
        Map<String, List<ServiceRecommendVO>> map=new HashMap<>();
        for (String functionName : functionNames) {
            Example example =new Example(ServiceRecommend.class);
            example.createCriteria().andEqualTo("functionName",functionName);
            List<ServiceRecommend> serviceRecommendList = serviceRecommendMapper.selectByExample(example);
            List<ServiceRecommendVO> serviceRecommendVOList = CopyUtil.copyList(serviceRecommendList, ServiceRecommendVO.class);
            map.put(functionName,serviceRecommendVOList);
        }
        return map;
    }
}
