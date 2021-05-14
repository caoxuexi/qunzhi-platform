package com.lq.mybatisgeneratordemo.controller;

import com.lq.mybatisgeneratordemo.dto.UserLoginParam;
import com.lq.mybatisgeneratordemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lq.mybatisgeneratordemo.common.api.CommonResult;

@Api(tags = "开发者管理")
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @ApiOperation("验证开发者的用户名和密码")
    public CommonResult login(@Validated @RequestBody UserLoginParam userLoginParam) {
        boolean res = userService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        if(res == false) {
            LOGGER.debug("用户登录失败，用户名或密码错误");
            return CommonResult.validateFailed("用户名或密码错误");
        }
        return CommonResult.success(true);
    }
}
