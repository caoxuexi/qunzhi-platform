package com.lq.mybatisgeneratordemo.service;

import com.lq.mybatisgeneratordemo.dto.SaberUserParam;
import com.lq.mybatisgeneratordemo.mbg.model.SaberUser;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    /**
     * 登录功能
     * 返回jwtToken
     */
    SaberUserParam login(String username, String password);

    /**
     * 开发者注册
     */
    boolean register(String username, String password);


    /**
     * 修改当前登录开发者个人信息
     */
    int update(SaberUserParam saberUserParam);
}
