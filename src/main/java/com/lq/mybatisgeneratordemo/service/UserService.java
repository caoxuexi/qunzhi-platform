package com.lq.mybatisgeneratordemo.service;

import com.lq.mybatisgeneratordemo.dto.SaberUserParam;
import com.lq.mybatisgeneratordemo.mbg.model.SaberUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    /**
     * 登录功能
     * 返回jwtToken
     */
    String login(String username, String password);

    /**
     * 开发者注册
     */
    boolean register(String username, String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 修改密码
     */
    @Transactional
    boolean updatePassword(String password);

    /**
     * 获取当前登录开发者
     */
    SaberUser getCurrentUser();
    /**
     * 修改当前登录开发者个人信息
     */
    int update(SaberUserParam saberUserParam);
}
