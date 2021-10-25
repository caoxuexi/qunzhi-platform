package com.xidian.qunzhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.xidian.qunzhi.core.UnifyResponse;
import com.xidian.qunzhi.exception.http.ForbiddenException;
import com.xidian.qunzhi.exception.http.UnknowException;
import com.xidian.qunzhi.pojo.dto.ChangePasswordDTO;
import com.xidian.qunzhi.pojo.dto.UserInformationDTO;
import com.xidian.qunzhi.pojo.vo.UserInformationVO;
import com.xidian.qunzhi.service.UserService;
import com.xidian.qunzhi.utils.LoginUserContext;
import com.xidian.qunzhi.utils.MD5Utils;
import com.xidian.qunzhi.utils.SnowFlake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.xidian.qunzhi.pojo.dto.UserLoginDTO;
import com.xidian.qunzhi.pojo.dto.UserRegistDTO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;


/**
 * @author Cao Study
 * @description <h1>UserController</h1>
 * @date 2021-10-17 22:33
 */
@Api(tags = "用户相关功能")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SnowFlake snowFlake;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "用户注册", httpMethod = "POST")
    @PostMapping(value = "/register")
    public UnifyResponse register(@RequestBody @Valid UserRegistDTO userRegistDTO, HttpServletRequest request) throws Exception {
        String rawPassword = userRegistDTO.getPassword();
        String password = MD5Utils.getMD5Str(rawPassword);
        //判断验证码是否正确
        String backgroundCaptcha = "";
        try {
            backgroundCaptcha = request.getSession().getAttribute("code").toString();
        }catch (Exception e){
            throw new ForbiddenException(20004);
        }
        if (!backgroundCaptcha.equals(userRegistDTO.getCaptcha())) {
            throw new ForbiddenException(20004);
        }
        //注册
        boolean result = userService.register(userRegistDTO, password);
        if (result) {
            return UnifyResponse.createSuccess(request);
        } else {
            throw new UnknowException();
        }
    }

    @ApiOperation(value = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public UserLoginVO login(@RequestBody @Valid UserLoginDTO userLoginDTO, HttpServletRequest request) throws Exception {
        UserLoginVO userLoginVO = userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        Long token = snowFlake.nextId();
        LOGGER.info("生成单点登录token：{}，并放入redis中", token);
        userLoginVO.setToken(token.toString());
        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginVO),
                3600 * 24, TimeUnit.SECONDS);
        return userLoginVO;
    }

    @ApiOperation(value = "管理员登录", httpMethod = "POST")
    @PostMapping("/adminLogin")
    public UserLoginVO adminLogin(@RequestBody @Valid UserLoginDTO userLoginDTO) throws Exception {
        UserLoginVO userLoginVO = userService.adminLogin(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        Long token = snowFlake.nextId();
        LOGGER.info("生成单点登录token：{}，并放入redis中", token);
        userLoginVO.setToken(token.toString());
        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginVO),
                3600 * 24, TimeUnit.SECONDS);
        return userLoginVO;
    }

    @ApiOperation(value = "用户密码修改", httpMethod = "POST")
    @PostMapping("/changePassword")
    public UnifyResponse changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO, HttpServletRequest request) throws Exception {
        UserLoginVO userLoginVO = LoginUserContext.getUser();
        userService.changePassword(changePasswordDTO, userLoginVO.getId());
        //删除用户原来的token
        redisTemplate.delete(userLoginVO.getToken());
        LOGGER.info("从redis中删除token: {}", userLoginVO.getToken());
        return UnifyResponse.commonSuccess(request);
    }

    @ApiOperation(value = "用户退出", httpMethod = "GET")
    @GetMapping(value = "/logout")
    public UnifyResponse logout(HttpServletRequest request) throws Exception {
        UserLoginVO userLoginVO = LoginUserContext.getUser();
        if (userLoginVO == null) {
            throw new ForbiddenException(20005);
        }
        redisTemplate.delete(userLoginVO.getToken());
        LOGGER.info("从redis中删除token: {}", userLoginVO.getToken());
        return UnifyResponse.commonSuccess(request);
    }

    @ApiOperation(value = "用户信息修改", httpMethod = "POST")
    @PostMapping("/changeInformation")
    public UserInformationVO changeInformation(@RequestBody @Valid UserInformationDTO userInformationDTO, HttpServletRequest request) throws Exception {
        UserLoginVO userLoginVO = LoginUserContext.getUser();
        UserInformationVO userInformationVO = userService.changeInformation(userInformationDTO, userLoginVO.getId());
        //删除用
        return userInformationVO;
    }

    @ApiOperation(value = "用户信息获取", httpMethod = "GET")
    @GetMapping("/getInformation")
    public UserInformationVO getInformation() throws Exception {
        UserLoginVO userLoginVO = LoginUserContext.getUser();
        UserInformationVO userInformationVO = userService.getInformation(userLoginVO.getId());
        return userInformationVO;
    }
}
