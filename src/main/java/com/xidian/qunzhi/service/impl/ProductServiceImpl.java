package com.xidian.qunzhi.service.impl;

import com.xidian.qunzhi.exception.http.NotFoundException;
import com.xidian.qunzhi.mapper.ProductMapper;
import com.xidian.qunzhi.mapper.UserMapper;
import com.xidian.qunzhi.pojo.Product;
import com.xidian.qunzhi.pojo.User;
import com.xidian.qunzhi.pojo.vo.ProductPreviewVO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import com.xidian.qunzhi.service.ProductService;
import com.xidian.qunzhi.utils.CopyUtil;
import org.springframework.beans.BeanUtils;
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

    @Override
    public List<ProductPreviewVO> listAllByEmail(UserLoginVO userLoginVO) {
        String userEmail=userLoginVO.getEmail();
        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email",userEmail);
        User user=userMapper.selectOneByExample(example);
        if(user==null){
            throw new NotFoundException(20002);
        }
        List<Product> productList= productMapper.listAllByUserId(user.getId());
        List<ProductPreviewVO> productPreviewVOList = CopyUtil.copyList(productList, ProductPreviewVO.class);
        return productPreviewVOList;
    }
}
