package com.xidian.qunzhi.service;

import com.xidian.qunzhi.pojo.vo.ProductDetailVO;
import com.xidian.qunzhi.pojo.vo.ProductPreviewVO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;

import java.util.List;

/**
 * @author Cao Study
 * @description <h1>ProductService</h1>
 * @date 2021-10-18 16:36
 */
public interface ProductService {

    /**
     * 获的当前用户所有的项目的缩略图
     * @param userLoginVO
     * @return
     */
    List<ProductPreviewVO> listAll(UserLoginVO userLoginVO);

    /**
     * 判断项目是否属于用户
     * @param productId
     * @param userId
     */
    void checkBelonging(Integer productId, Integer userId);

    /**
     * 根据项目名称搜索项目的详细信息
     * @param productId
     * @return
     */
    ProductDetailVO detail(Integer productId);
}
