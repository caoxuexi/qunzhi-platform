package com.xidian.qunzhi.service.impl;

import com.xidian.qunzhi.exception.http.ForbiddenException;
import com.xidian.qunzhi.mapper.ProductMapper;
import com.xidian.qunzhi.mapper.UserMapper;
import com.xidian.qunzhi.mapper.UserProjectMapper;
import com.xidian.qunzhi.pojo.Product;
import com.xidian.qunzhi.pojo.UserProject;
import com.xidian.qunzhi.pojo.vo.ProductDetailVO;
import com.xidian.qunzhi.pojo.vo.ProductPreviewVO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import com.xidian.qunzhi.service.ProductService;
import com.xidian.qunzhi.utils.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Cao Study
 * @description <h1>ProductServiceImpl</h1>
 * @date 2021-10-18 16:36
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserProjectMapper userProjectMapper;

    @Override
    public List<ProductPreviewVO> listAll(UserLoginVO userLoginVO) {
        List<Product> productList= productMapper.listAllByUserId(userLoginVO.getId());
        List<ProductPreviewVO> productPreviewVOList = CopyUtil.copyList(productList, ProductPreviewVO.class);
        return productPreviewVOList;
    }

    @Override
    public void checkBelonging(Integer productId, Integer usrId) {
        Example example=new Example(UserProject.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId",productId)
                .andEqualTo("userId",usrId);

        UserProject userProject = userProjectMapper.selectOneByExample(example);
        if(userProject==null) {
            //如果该项目不属于当前用户则抛出异常
            throw new ForbiddenException(30002);
        }
    }

    @Override
    public ProductDetailVO detail(Integer productId) {
        Example example=new Example(UserProject.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",productId)
                .andEqualTo("deleteTime",null);
        Product product = productMapper.selectOneByExample(example);
        ProductDetailVO productDetailVO = CopyUtil.copy(product, ProductDetailVO.class);
        return productDetailVO;
    }
}
