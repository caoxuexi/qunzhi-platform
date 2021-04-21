package com.lq.mybatisgeneratordemo.controller;

import com.lq.mybatisgeneratordemo.dto.AdminLoginParam;
import com.lq.mybatisgeneratordemo.mbg.model.SaberUser;
import com.lq.mybatisgeneratordemo.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.lq.mybatisgeneratordemo.common.api.CommonResult;
import com.lq.mybatisgeneratordemo.common.api.CommonPage;

@Api(tags = "AdminController", description = "用户管理")
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @ApiOperation("验证管理员的用户名与密码")
    @PostMapping("/login")
    @ResponseBody
    public CommonResult login(@Validated @RequestBody AdminLoginParam adminLoginParam) {
        boolean res = adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword());
        if(res == false) {
            LOGGER.debug("用户登录失败，用户名或密码错误");
            return CommonResult.validateFailed("用户名或密码错误");
        }
        return CommonResult.success(true);
    }


    //@GetMapping("/userlist")
    //public CommonResult<CommonPage<SaberUser>> listUser(@RequestParam(value = "pageNum"))
}
