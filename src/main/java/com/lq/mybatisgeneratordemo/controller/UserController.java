package com.lq.mybatisgeneratordemo.controller;

import com.lq.mybatisgeneratordemo.dto.SaberUserParam;
import com.lq.mybatisgeneratordemo.dto.UserLoginParam;
import com.lq.mybatisgeneratordemo.mbg.model.SaberUser;
import com.lq.mybatisgeneratordemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.lq.mybatisgeneratordemo.common.api.CommonResult;


@Api(tags = "用户相关功能")
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    @ResponseBody
    public CommonResult<Boolean> register(@RequestParam String username, @RequestParam String password) {
        boolean result = userService.register(username, password);
        if (!result) {
            return CommonResult.failed();
        }
        return CommonResult.success(true);
    }


    @ApiOperation("用户登录")
    @PostMapping("/login")
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UserLoginParam userLoginParam) {
        SaberUserParam userParam = userService.login(userLoginParam.getUsername(), userLoginParam.getPassword());
        if(userParam == null)
            return CommonResult.failed("用户名或密码错误");
        return CommonResult.success(userParam);
    }


    @ApiOperation("修改当前用户信息")
    @PostMapping("/update")
    @ResponseBody
    public CommonResult update(@RequestBody SaberUserParam saberUserParam) {
        int count = userService.update(saberUserParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

}
