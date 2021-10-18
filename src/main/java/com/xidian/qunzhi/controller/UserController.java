package com.xidian.qunzhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.xidian.qunzhi.QunzhiApplication;
import com.xidian.qunzhi.core.UnifyResponse;
import com.xidian.qunzhi.exception.http.ForbiddenException;
import com.xidian.qunzhi.exception.http.NotFoundException;
import com.xidian.qunzhi.exception.http.UnknowException;
import com.xidian.qunzhi.service.UserService;
import com.xidian.qunzhi.utils.JsonUtils;
import com.xidian.qunzhi.utils.MD5Utils;
import com.xidian.qunzhi.utils.SnowFlake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;


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
    public void register(@RequestBody @Valid UserRegistDTO userRegistDTO, HttpServletRequest request) throws Exception {
        String rawPassword = userRegistDTO.getPassword();
        String password = MD5Utils.getMD5Str(rawPassword);
        String backgroundCaptcha = request.getSession().getAttribute("code").toString();
        if (!backgroundCaptcha.equals(userRegistDTO.getCaptcha())) {
            throw new ForbiddenException(20004);
        }
        boolean result = userService.register(userRegistDTO.getEmail(), password);
        if (result) {
            UnifyResponse.createSuccess();
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

    @ApiOperation(value = "用户退出", httpMethod = "GET")
    @GetMapping(value = "/logout/{token}")
    public void logout(@PathVariable
                           @ApiParam(name = "用户授权token",defaultValue = "105306861133238272")
                                   String token) throws Exception {
        redisTemplate.delete(token);
        LOGGER.info("从redis中删除token: {}", token);
        UnifyResponse.commonSuccess();
    }
}
