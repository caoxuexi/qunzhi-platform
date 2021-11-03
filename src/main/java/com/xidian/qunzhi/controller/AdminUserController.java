package com.xidian.qunzhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.xidian.qunzhi.core.UnifyResponse;
import com.xidian.qunzhi.exception.http.ForbiddenException;
import com.xidian.qunzhi.pojo.basic.PageVO;
import com.xidian.qunzhi.pojo.dto.SearchProjectDTO;
import com.xidian.qunzhi.pojo.dto.SearchUserDTO;
import com.xidian.qunzhi.pojo.dto.UserLoginDTO;
import com.xidian.qunzhi.pojo.vo.ProjectAdminVO;
import com.xidian.qunzhi.pojo.vo.StatisticVO;
import com.xidian.qunzhi.pojo.vo.UserAdminVO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import com.xidian.qunzhi.service.AdminUserService;
import com.xidian.qunzhi.service.ProjectService;
import com.xidian.qunzhi.service.UserService;
import com.xidian.qunzhi.utils.LoginUserContext;
import com.xidian.qunzhi.utils.SnowFlake;
import io.micrometer.core.instrument.Statistic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * @author Cao Study
 * @description <h1>AdminUserController</h1>
 * @date 2021-10-25 21:49
 */
@Api(tags = "管理员相关功能")
@RestController
@RequestMapping("/adminUser")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "管理员登录", httpMethod = "POST")
    @PostMapping("/login")
    public UserLoginVO adminLogin(@RequestBody @Valid UserLoginDTO userLoginDTO) throws Exception {
        UserLoginVO userLoginVO = adminUserService.adminLogin(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        return userLoginVO;
    }

    @ApiOperation(value = "管理员退出", httpMethod = "GET")
    @GetMapping(value = "/logout")
    public UnifyResponse logout(HttpServletRequest request) throws Exception {
        UserLoginVO userLoginVO = LoginUserContext.getUser();
        if (userLoginVO == null) {
            throw new ForbiddenException(20005);
        }
        userService.logout(userLoginVO.getId());

        return UnifyResponse.commonSuccess(request);
    }

    @ApiOperation(value = "管理员按条件获取用户信息", httpMethod = "GET")
    @GetMapping("/searchUser")
    public PageVO<UserAdminVO> searchByAdmin(@Valid SearchUserDTO searchUserDTO){
        UserLoginVO userLoginVO = LoginUserContext.getUser();
        //分页查询
        PageVO<UserAdminVO> userAdminVOList= userService.searchByAdmin(searchUserDTO,userLoginVO);
        return userAdminVOList;
    }


    @ApiOperation(value = "管理员按条件获取项目信息",httpMethod = "GET")
    @GetMapping("/searchProject")
    public  PageVO<ProjectAdminVO> searchByAdmin(@Valid SearchProjectDTO searchProjectDTO){
        UserLoginVO userLoginVO = LoginUserContext.getUser();
        //分页查询
        PageVO<ProjectAdminVO> projectAdminVOList= projectService.searchByAdmin(searchProjectDTO,userLoginVO);
        return projectAdminVOList;
    }


    @ApiOperation(value = "管理员获取项目总数和用户总数", httpMethod = "GET")
    @GetMapping("/getStatistic")
    public StatisticVO getStatistic(){
        UserLoginVO userLoginVO = LoginUserContext.getUser();
        //查询总的项目数
        StatisticVO statisticVO= adminUserService.getStatistic(userLoginVO);
        return statisticVO;
    }
}
