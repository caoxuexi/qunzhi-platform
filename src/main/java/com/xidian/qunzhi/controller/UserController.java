package com.xidian.qunzhi.controller;

import com.xidian.qunzhi.core.UnifyResponse;
import com.xidian.qunzhi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pojo.dto.UserLoginDTO;
import pojo.dto.UserRegistDTO;
import pojo.vo.UserLoginVO;

import javax.validation.Valid;


@Api(tags = "用户相关功能")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "用户注册",httpMethod = "POST")
    @PostMapping(value = "/register")
    public void register(@RequestBody @Valid UserRegistDTO useruserRegistDTO) {
        boolean result = userService.register(useruserRegistDTO.getEmail(), useruserRegistDTO.getPassword());
        if (result){
            UnifyResponse.createSuccess(1);
        }
    }


    @ApiOperation(value = "用户登录",httpMethod = "POST")
    @PostMapping("/login")
    public UserLoginVO login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        UserLoginVO userVO=userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        return null;
    }

}
