package com.xidian.qunzhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.xidian.qunzhi.core.UnifyResponse;
import com.xidian.qunzhi.exception.http.ForbiddenException;
import com.xidian.qunzhi.pojo.dto.UserLoginDTO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import com.xidian.qunzhi.service.AdminUserService;
import com.xidian.qunzhi.service.UserService;
import com.xidian.qunzhi.utils.LoginUserContext;
import com.xidian.qunzhi.utils.SnowFlake;
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
    private SnowFlake snowFlake;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "管理员登录", httpMethod = "POST")
    @PostMapping("/login")
    public UserLoginVO adminLogin(@RequestBody @Valid UserLoginDTO userLoginDTO) throws Exception {
        UserLoginVO userLoginVO = adminUserService.adminLogin(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        Long token = snowFlake.nextId();
        LOGGER.info("生成单点登录token：{}，并放入redis中", token);
        userLoginVO.setToken(token.toString());
        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginVO),
                3600 * 24, TimeUnit.SECONDS);
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
}
