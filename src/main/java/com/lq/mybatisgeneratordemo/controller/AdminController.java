package com.lq.mybatisgeneratordemo.controller;

import com.lq.mybatisgeneratordemo.dto.AdminLoginParam;
import com.lq.mybatisgeneratordemo.mbg.model.SaberUser;
import com.lq.mybatisgeneratordemo.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.lq.mybatisgeneratordemo.common.api.CommonResult;
import com.lq.mybatisgeneratordemo.common.api.CommonPage;

import java.util.List;

@Api(tags = "管理员相关功能")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @ApiOperation("验证管理员的用户名与密码")
    @PostMapping("/login")
    public CommonResult login(@Validated @RequestBody AdminLoginParam adminLoginParam) {
        boolean res = adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword());
        if(res == false) {
            LOGGER.debug("用户登录失败，用户名或密码错误");
            return CommonResult.failed("用户名或密码错误");
        }
        return CommonResult.success(true);
    }

    @ApiOperation("根据用户名分页获取开发者列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<SaberUser>> list(@ApiParam(name = "keyword", value = "用户名关键字") @RequestParam(value = "keyword", required = false) String keyword,
                                                   @ApiParam(name = "pageSize", value = "页面大小")@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                   @ApiParam(name = "pageNum", value = "页面号")@RequestParam(value = "pageNum", defaultValue = "1", required = false)Integer pageNum) {
        List<SaberUser> adminList = adminService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }


    @ApiOperation("获取指定开发者信息")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public CommonResult<SaberUser> getItem(@PathVariable int id) {
        SaberUser user = adminService.getUser((long) id);
        return CommonResult.success(user);
    }

}
