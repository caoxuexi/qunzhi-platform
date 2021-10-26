package com.xidian.qunzhi.controller;

import com.xidian.qunzhi.core.UnifyResponse;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import com.xidian.qunzhi.pojo.vo.UserProjectVO;
import com.xidian.qunzhi.service.ProjectGroupService;
import com.xidian.qunzhi.utils.LoginUserContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Cao Study
 * @description <h1>ProjecctGroupController</h1>
 * @date 2021-10-23 21:15
 */
@Api(tags = "项目组相关功能")
@RestController
@RequestMapping("projectGroup")
public class ProjecctGroupController {

    @Autowired
    ProjectGroupService projectGroupService;

    @ApiOperation(value = "添加项目组成员",httpMethod = "GET")
    @GetMapping("/addProjectMember")
    public UnifyResponse addProjectMember(@ApiParam(value = "项目id",example = "1") @RequestParam(value = "id") Integer projectId,
                                          @ApiParam(value = "用户id",example = "1") Integer userId,
                                          HttpServletRequest request){
        UserLoginVO userLoginVO=LoginUserContext.getUser();
        projectGroupService.addProjectMember(projectId,userId,userLoginVO.getId());
        return UnifyResponse.commonSuccess(request);
    }

    @ApiOperation(value = "删除项目组成员",httpMethod = "GET")
    @GetMapping("/deleteProjectMember")
    public UnifyResponse deleteProjectMember(@ApiParam(value = "项目id",example = "1") @RequestParam(value = "id") Integer projectId,
                                             @ApiParam(value = "用户id",example = "1") Integer userId,
                                             HttpServletRequest request){
        UserLoginVO userLoginVO= LoginUserContext.getUser();
        projectGroupService.deleteProjectMember(projectId,userId,userLoginVO.getId());
        return UnifyResponse.commonSuccess(request);
    }

    @ApiOperation(value = "申请项目组成员",httpMethod = "GET")
    @GetMapping("/applyProjectMember")
    public UnifyResponse applyProjectMember(@ApiParam(value = "项目id",example = "1") @RequestParam(value = "id") Integer projectId,
                                            HttpServletRequest request){
        UserLoginVO userLoginVO=LoginUserContext.getUser();
        projectGroupService.applyProjectMember(projectId,userLoginVO.getId());
        return UnifyResponse.commonSuccess(request);
    }

    @ApiOperation(value = "获取参加项目请求",httpMethod = "GET")
    @GetMapping("/getApplication")
    public UnifyResponse getApplication(@ApiParam(value = "项目id",example = "1") @RequestParam(value = "id") Integer projectId,
                                        HttpServletRequest request){
        UserLoginVO userLoginVO=LoginUserContext.getUser();
        projectGroupService.getApplication(projectId,userLoginVO.getId());
        return UnifyResponse.commonSuccess(request);
    }


    @ApiOperation(value = "查看项目组成员",httpMethod = "GET")
    @GetMapping("/getProjectMember")
    public  List<UserProjectVO>  getProjectMember(@ApiParam(value = "项目id",example = "1") @RequestParam(value = "id") Integer projectId,
                                          HttpServletRequest request){
        UserLoginVO userLoginVO=LoginUserContext.getUser();
        List<UserProjectVO> userProjectVOList= projectGroupService.getProjectMember(projectId,userLoginVO.getId());
        return  userProjectVOList;
    }

}
