package com.lq.mybatisgeneratordemo.service.impl;

import com.lq.mybatisgeneratordemo.mbg.mapper.SaberAdminMapper;
import com.lq.mybatisgeneratordemo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImpl implements AdminService {
    @Autowired
    private SaberAdminMapper saberAdminMapper;

    @Override
    public boolean login(String username, String password) {
        
        return false;
    }
}
