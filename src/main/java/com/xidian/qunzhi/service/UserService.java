package com.xidian.qunzhi.service;


import com.xidian.qunzhi.pojo.vo.UserLoginVO;

public interface UserService {
    /**
     * 开发者注册
     */
    boolean register(String email, String password);


    UserLoginVO login(String email, String password) throws Exception;
}
