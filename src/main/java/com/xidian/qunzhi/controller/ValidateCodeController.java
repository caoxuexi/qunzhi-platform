package com.xidian.qunzhi.controller;

import com.xidian.qunzhi.service.impl.UserServiceImpl;
import com.xidian.qunzhi.utils.CookieUtils;
import com.xidian.qunzhi.utils.SnowFlake;
import com.xidian.qunzhi.utils.ValidateCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author Cao Study
 * @description <h1>ValidateCodeController</h1>
 * @date 2021-10-17 23:02
 */
@RestController
@Api(tags = "验证码功能")
@RequestMapping("/captcha")
public class ValidateCodeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateCodeController.class);
    public static HashMap<String,HttpSession> sessionMap=new HashMap<>();

    @Autowired
    private SnowFlake snowFlake;

    @ApiOperation(value = "生成验证码",httpMethod = "GET")
    @GetMapping(value="/generate")
    public String generateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "-1");
        //---------------------------------------------------------------
        ////生成获取session的token
        //Long sessionKey=snowFlake.nextId();
        //response.setHeader("sessionKey",sessionKey.toString());
        ////将session存入map进行保存
        //HttpSession session = request.getSession();
        //sessionMap.put(sessionKey.toString(),session);
        //------------------------------------------------------------
        HttpSession session = request.getSession();
        ValidateCode vCode = new ValidateCode(120,40,5,100);
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());
        LOGGER.info(vCode.getCode());
        LOGGER.info("Session ID:"+session.getId());

        return null;
    }
}
