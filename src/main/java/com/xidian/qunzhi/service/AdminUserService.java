package com.xidian.qunzhi.service;

import com.xidian.qunzhi.pojo.vo.UserLoginVO;

/**
 * @author Cao Study
 * @description <h1>AdminUserService</h1>
 * @date 2021-10-25 21:52
 */
public interface AdminUserService {
    /**
     * 管理员登录
     * @param email
     * @param password
     * @return 登录用户VO
     */
    UserLoginVO adminLogin(String email, String password) throws Exception;
}
