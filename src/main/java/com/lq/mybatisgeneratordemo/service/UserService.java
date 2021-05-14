package com.lq.mybatisgeneratordemo.service;

public interface UserService {
    /**
     * @param username
     * @param password
     * @return
     * 登录功能
     */
    boolean login(String username, String password);


}
