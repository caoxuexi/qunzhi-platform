package com.lq.mybatisgeneratordemo.service.impl;

import com.lq.mybatisgeneratordemo.mbg.mapper.SaberUserMapper;
import com.lq.mybatisgeneratordemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SaberUserMapper saberUserMapper;

    @Override
    public boolean login(String username, String password) {

        return false;
    }
}
