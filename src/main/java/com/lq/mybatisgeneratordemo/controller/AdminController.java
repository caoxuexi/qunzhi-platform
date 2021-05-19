package com.lq.mybatisgeneratordemo.controller;

import com.lq.mybatisgeneratordemo.dto.AdminLoginParam;
import com.lq.mybatisgeneratordemo.dto.SaberUserParam;
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

import java.util.List;

@Api(tags = "用户管理")
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

    @ApiOperation("根据用户名分页获取开发者列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SaberUser>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SaberUser> adminList = adminService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }


    @ApiOperation("获取指定开发者信息")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<SaberUser> getItem(@PathVariable int id) {
        SaberUser user = adminService.getUser(id);
        return CommonResult.success(user);
    }

    //尚未测试
    @ApiOperation(value = "更新指定开发者信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable("id") Integer id,
                               @Validated @RequestBody SaberUserParam saberUserParam) {
        CommonResult commonResult;
        int count = adminService.update(id, saberUserParam);
        if (count == 1) {
            commonResult = CommonResult.success(count);
        } else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }


}
