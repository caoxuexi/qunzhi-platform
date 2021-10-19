package com.xidian.qunzhi.mapper;


import com.xidian.qunzhi.pojo.Product;
import com.xidian.qunzhi.utils.MyMapper;

import java.util.List;

public interface ProductMapper extends MyMapper<Product> {
    List<Product> listAllByUserId(Integer id);
}