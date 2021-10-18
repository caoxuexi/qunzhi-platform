package com.xidian.qunzhi.controller;

import com.xidian.qunzhi.utils.JythonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cao Study
 * @description <h1>ProductController</h1>
 * @date 2021-10-17 22:33
 */
@Api(tags = "产品相关功能")
@RestController
public class ProductController {
    @ApiOperation(value = "关键词抽取",httpMethod = "POST")
    @PostMapping("/keyext")
    public String KeywordExtraction (@RequestParam String demands){
        String result = JythonUtil.ext(demands);
        return result;
    }
}
