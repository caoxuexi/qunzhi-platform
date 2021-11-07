package com.xidian.qunzhi.service.impl;

import com.xidian.qunzhi.mapper.ServiceRecommendMapper;
import com.xidian.qunzhi.pojo.ServiceRecommend;
import com.xidian.qunzhi.pojo.vo.ServiceRecommendVO;
import com.xidian.qunzhi.service.ServiceRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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
    public List<ServiceRecommendVO> getService(List<String> functionNames) {
        List<ServiceRecommendVO> serviceRecommendVOList=new ArrayList<>();
        for (String functionName : functionNames) {
            //按照功能名，在数据库中寻找服务
            ServiceRecommendVO serviceRecommendVO= new ServiceRecommendVO();
            Example example =new Example(ServiceRecommend.class);
            example.createCriteria().andEqualTo("functionName",functionName);
            List<ServiceRecommend> serviceRecommendList = serviceRecommendMapper.selectByExample(example);
            //填充serviceRecommendVO
            serviceRecommendVO.setFunName(functionName);
            List<String> serviceNameList=new ArrayList<>();
            for (ServiceRecommend serviceRecommend : serviceRecommendList) {
                serviceNameList.add(serviceRecommend.getServiceName());
            }
            serviceRecommendVO.setMicroservice(serviceNameList);
            serviceRecommendVO.setNumber(serviceNameList.size());
            serviceRecommendVOList.add(serviceRecommendVO);
        }
        return serviceRecommendVOList;
    }

    @Override
    public Map<String,String> getCodsUrl(String funcName) {
        Example example =new Example(ServiceRecommend.class);
        example.createCriteria().andEqualTo("functionName",funcName);
        List<ServiceRecommend> serviceRecommends = serviceRecommendMapper.selectByExample(example);
        HashMap<String, String> codsMap = new HashMap<>();
        for (ServiceRecommend serviceRecommend : serviceRecommends) {
            codsMap.put(serviceRecommend.getServiceName(),serviceRecommend.getCodeUrl());
        }
        return codsMap;
    }
}
