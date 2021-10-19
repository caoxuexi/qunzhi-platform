package com.xidian.qunzhi.controller;

import com.xidian.qunzhi.core.UnifyResponse;
import com.xidian.qunzhi.pojo.vo.ProjectDetailVO;
import com.xidian.qunzhi.pojo.vo.ProjectPreviewVO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import com.xidian.qunzhi.service.ProjectService;
import com.xidian.qunzhi.utils.JythonUtil;
import com.xidian.qunzhi.utils.LoginUserContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        List<ProjectPreviewVO> productPreviewVOList= projectService.listAll(userLoginVO);
        return productPreviewVOList;
    }

    @ApiOperation(value = "管理员列出所有项目的情况",httpMethod = "GET")
    @GetMapping("/listAll")
    public List<ProjectPreviewVO> listAll(){
        UserLoginVO userLoginVO = LoginUserContext.getUser();

        List<ProjectPreviewVO> productPreviewVOList= projectService.listAll(userLoginVO);
        return productPreviewVOList;
    }

    @ApiOperation(value = "获取项目的详细内容",httpMethod = "GET")
    @GetMapping("/detail")
    public ProjectDetailVO detail(@ApiParam(value = "项目id",example = "1")
                                       @RequestParam(value = "id") Integer projectId){
        //判断该项目是否属于当前用户
        UserLoginVO userLoginVO=LoginUserContext.getUser();
        projectService.checkBelonging(projectId,userLoginVO.getId());
        ProjectDetailVO productDetailVO = projectService.detail(projectId);
        return productDetailVO;
    }

    @ApiOperation(value = "获取项目的详细内容",httpMethod = "DELETE")
    @DeleteMapping("/delete")
    public void delete(@ApiParam(value = "项目id",example = "1")
                                  @RequestParam(value = "id") Integer projectId){
        //判断该项目是否属于当前用户
        UnifyResponse.deleteSuccess();
    }

    @ApiOperation(value = "关键词抽取",httpMethod = "POST")
    @PostMapping("/keyext")
    public String KeywordExtraction (@RequestParam String demands){
        String result = JythonUtil.ext(demands);
        return result;
    }


}
