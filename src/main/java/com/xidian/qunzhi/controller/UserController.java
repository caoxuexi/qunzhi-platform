package com.xidian.qunzhi.controller;

import com.xidian.qunzhi.core.UnifyResponse;
import com.xidian.qunzhi.exception.http.HttpException;
import com.xidian.qunzhi.exception.http.UnknowException;
import com.xidian.qunzhi.service.UserService;
import com.xidian.qunzhi.utils.MD5Utils;
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
    public void register(@RequestBody @Valid UserRegistDTO useruserRegistDTO) throws Exception {
        String rawPassword= useruserRegistDTO.getPassword();
        String password= MD5Utils.getMD5Str(rawPassword);
        boolean result = userService.register(useruserRegistDTO.getEmail(), password);
        if (result){
            UnifyResponse.createSuccess(1);
        }else{
            throw new UnknowException();
        }
    }


    @ApiOperation(value = "用户登录",httpMethod = "POST")
    @PostMapping("/login")
    public UserLoginVO login(@RequestBody @Valid UserLoginDTO userLoginDTO) throws Exception {
        UserLoginVO userLoginVO=userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        return userLoginVO;
    }

}
