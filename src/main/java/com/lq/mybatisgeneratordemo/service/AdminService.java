package com.lq.mybatisgeneratordemo.service;

import com.lq.mybatisgeneratordemo.mbg.model.SaberAdmin;
import com.lq.mybatisgeneratordemo.mbg.model.SaberUser;

import java.util.List;

public interface AdminService {

    /**
     * @param username
     * @param password
     * @return
     * 登录功能
     */
    boolean login(String username, String password);

    /**
     * 根据用户名获取后台管理员
     */
    SaberAdmin getAdminByUsername(String username);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<SaberUser> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 根据用户id获取用户
     */
    SaberUser getUser(Long id);

}
