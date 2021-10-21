package com.xidian.qunzhi.service;


import com.xidian.qunzhi.pojo.vo.UserLoginVO;

public interface UserService {
    /**
     * 开发者注册
     */
    boolean register(String email, String password);


    /**
     * 普通用户登录
     * @param email
     * @param password
     * @return 登录用户VO
     * @throws Exception
     */
    UserLoginVO login(String email, String password) throws Exception;

    /**
     * 管理员登录
     * @param email
     * @param password
     * @return 登录用户VO
     */
    UserLoginVO adminLogin(String email, String password) throws Exception;
}
