package com.xidian.qunzhi.utils;

import com.xidian.qunzhi.pojo.vo.UserLoginVO;

import java.io.Serializable;

/**
 * 使用线程安全的方式存储用户信息
 */
public class LoginUserContext implements Serializable {

    private static ThreadLocal<UserLoginVO> user = new ThreadLocal<>();

    public static UserLoginVO getUser() {
        return user.get();
    }

    public static void setUser(UserLoginVO user) {
        LoginUserContext.user.set(user);
    }

}
