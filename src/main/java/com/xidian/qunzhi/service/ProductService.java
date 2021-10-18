package com.xidian.qunzhi.service;

import com.xidian.qunzhi.pojo.vo.ProductPreviewVO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;

import java.util.List;

/**
 * @author Cao Study
 * @description <h1>ProductService</h1>
 * @date 2021-10-18 16:36
 */
public interface ProductService {

    List<ProductPreviewVO> listAllByEmail(UserLoginVO userLoginVO);
}
