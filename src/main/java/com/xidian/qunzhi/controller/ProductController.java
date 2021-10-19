package com.xidian.qunzhi.controller;

import com.xidian.qunzhi.pojo.Product;
import com.xidian.qunzhi.pojo.vo.ProductDetailVO;
import com.xidian.qunzhi.pojo.vo.ProductPreviewVO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import com.xidian.qunzhi.service.ProductService;
import com.xidian.qunzhi.utils.JythonUtil;
import com.xidian.qunzhi.utils.LoginUserContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Cao Study
 * @description <h1>ProductController</h1>
 * @date 2021-10-17 22:33
 */
@Api(tags = "产品相关功能")
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @ApiOperation(value = "列出当前用户所在的所有项目缩列图",httpMethod = "GET")
    @GetMapping("/listall")
    public List<ProductPreviewVO> listallByEmial(){
        UserLoginVO userLoginVO = LoginUserContext.getUser();
        List<ProductPreviewVO> productPreviewVOList=productService.listAll(userLoginVO);
        return productPreviewVOList;
    }

    @ApiOperation(value = "获取项目的详细内容",httpMethod = "GET")
    @GetMapping("/detail")
    public  ProductDetailVO detail(@ApiParam(value = "项目id",example = "1")
                                       @RequestParam(value = "id") Integer productId){
        //判断该项目是否属于当前用户
        UserLoginVO userLoginVO=LoginUserContext.getUser();
        productService.checkBelonging(productId,userLoginVO.getId());
        ProductDetailVO productDetailVO = productService.detail(productId);
        return productDetailVO;
    }

    @ApiOperation(value = "关键词抽取",httpMethod = "POST")
    @PostMapping("/keyext")
    public String KeywordExtraction (@RequestParam String demands){
        String result = JythonUtil.ext(demands);
        return result;
    }
}
