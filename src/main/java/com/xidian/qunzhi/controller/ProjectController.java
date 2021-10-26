package com.xidian.qunzhi.controller;

import com.xidian.qunzhi.core.UnifyResponse;
import com.xidian.qunzhi.pojo.basic.PageVO;
import com.xidian.qunzhi.pojo.dto.ProjectDTO;
import com.xidian.qunzhi.pojo.dto.SearchProjectDTO;
import com.xidian.qunzhi.pojo.vo.*;
import com.xidian.qunzhi.service.ProjectService;

import com.xidian.qunzhi.utils.LoginUserContext;
import com.xidian.qunzhi.utils.NLPUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Cao Study
 * @description <h1>ProductController</h1>
 * @date 2021-10-17 22:33
 */
@Api(tags = "项目相关功能")
@RestController
@RequestMapping("project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;


    @ApiOperation(value = "列出当前用户所在的所有项目缩列图",httpMethod = "GET")
    @GetMapping("/listAllByUser")
    public List<ProjectPreviewVO> listAllByUser(){
        UserLoginVO userLoginVO = LoginUserContext.getUser();
        List<ProjectPreviewVO> productPreviewVOList= projectService.listAllByUser(userLoginVO);
        return productPreviewVOList;
    }


    @ApiOperation(value = "获取项目的详细内容",httpMethod = "GET")
    @GetMapping("/detail")
    public ProjectDetailVO detail(@ApiParam(value = "项目id",example = "1")
                                       @RequestParam(value = "id") Integer projectId){
        UserLoginVO userLoginVO=LoginUserContext.getUser();
        ProjectDetailVO productDetailVO = projectService.detail(projectId,userLoginVO.getId());
        return productDetailVO;
    }

    @ApiOperation(value = "新建项目",httpMethod = "POST")
    @GetMapping("/create")
    public ProjectDetailVO create(ProjectDTO projectDTO){
        UserLoginVO userLoginVO=LoginUserContext.getUser();
        ProjectDetailVO productDetailVO = projectService.create(projectDTO,userLoginVO.getId());
        return productDetailVO;
    }

    @ApiOperation(value = "修改项目信息",httpMethod = "POST")
    @GetMapping("/update")
    public ProjectDetailVO update(ProjectDTO projectDTO){
        UserLoginVO userLoginVO=LoginUserContext.getUser();
        ProjectDetailVO productDetailVO = projectService.update(projectDTO,userLoginVO.getId());
        return productDetailVO;
    }

    @ApiOperation(value = "删除项目",httpMethod = "DELETE")
    @DeleteMapping("/delete")
    public UnifyResponse delete(@ApiParam(value = "项目id",example = "1")
                                  @RequestParam(value = "id") Integer projectId, HttpServletRequest request){
        UserLoginVO userLoginVO=LoginUserContext.getUser();
        projectService.delete(projectId,userLoginVO.getId());
        return UnifyResponse.deleteSuccess(request);
    }

    @ApiOperation(value = "获取日志",httpMethod = "GET")
    @GetMapping("/getLogs")
    public List<DeviceLogVO> getLogs (@RequestParam(value = "id")  Integer projectId){
        UserLoginVO userLoginVO=LoginUserContext.getUser();
        List<DeviceLogVO> deviceLogVO = projectService.logs(projectId, userLoginVO.getId());
        return deviceLogVO;
    }

    @ApiOperation(value = "关键词抽取",httpMethod = "POST")
    @PostMapping("/keyext")
    public String KeywordExtraction (@RequestParam String demands){
        String result = NLPUtil.ext(demands);
        return result;
    }
}
