package com.xidian.qunzhi.controller;

import com.xidian.qunzhi.pojo.ServiceRecommend;
import com.xidian.qunzhi.pojo.dto.ServiceRecommendDTO;
import com.xidian.qunzhi.pojo.vo.ProjectPreviewVO;
import com.xidian.qunzhi.pojo.vo.ServiceRecommendVO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import com.xidian.qunzhi.service.ServiceRecommendService;
import com.xidian.qunzhi.utils.LoginUserContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cao Study
 * @description <h1>ServiceController</h1>
 * @date 2021-11-04 12:09
 */
@Api(tags = "服务图谱映射和代码生成相关功能")
@RestController
@RequestMapping("service")
@Validated
public class ServiceRecommendController {
    @Autowired
    private ServiceRecommendService serviceRecommendService;

    @ApiOperation(value = "列出当前传入字段对应的推荐服务",httpMethod = "POST")
    @PostMapping("/getService")
    public  List<ServiceRecommendVO>  getService(@RequestBody ServiceRecommendDTO serviceRecommendDTO){
        UserLoginVO userLoginVO = LoginUserContext.getUser();
        List<String> functionNames = serviceRecommendDTO.getFunctionNames();
        List<ServiceRecommendVO> serviceRecommendVOList= serviceRecommendService.getService(functionNames);
        return serviceRecommendVOList;
    }

    @ApiOperation(value = "列出当前传入字段对应的推荐服务的代码连接",httpMethod = "POST")
    @PostMapping("/getCods")
    public Map getCods(String funcName){
        Map<String, String> codsMap = serviceRecommendService.getCodsUrl(funcName);
        return codsMap;
    }
}
