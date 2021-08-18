package com.lq.mybatisgeneratordemo.service.impl;

import com.lq.mybatisgeneratordemo.dto.SaberUserParam;
import com.lq.mybatisgeneratordemo.mbg.mapper.SaberUserMapper;
import com.lq.mybatisgeneratordemo.mbg.model.SaberUser;
import com.lq.mybatisgeneratordemo.mbg.model.SaberUserExample;
import com.lq.mybatisgeneratordemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private SaberUserMapper saberUserMapper;

    @Override
    public boolean register(String username, String password) {
        SaberUser saberUser = new SaberUser();
        saberUser.setPassword(password);
        saberUser.setUsername(username);
        //查询是否有相同用户名的用户
        SaberUserExample saberUserExample = new SaberUserExample();
        saberUserExample.createCriteria().andUsernameEqualTo(username);
        List<SaberUser> userList = saberUserMapper.selectByExample(saberUserExample);
        if (userList.size() > 0) {
            return false;
        }
        LOGGER.info("用户" + saberUser.toString());
        int count = saberUserMapper.insertSelective(saberUser);
        return count != 0;
    }

    @Override
    public int update(SaberUserParam saberUserParam) {
        SaberUser user = new SaberUser();
        BeanUtils.copyProperties(saberUserParam, user);
        LOGGER.info(user.toString());
        int count = saberUserMapper.updateByPrimaryKeySelective(user);
        return count;
    }

    @Override
    public SaberUserParam login(String username, String password) {
        SaberUser user = getByUsername(username);
        if(user == null) {
            return null;
        }
        if(!user.getPassword().equals(password)) {
            return null;
        }
        SaberUserParam userParam = new SaberUserParam();
        BeanUtils.copyProperties(user, userParam);
        return userParam;
    }


    public SaberUser getByUsername(String username) {
        SaberUser user;
        SaberUserExample saberUserExample = new SaberUserExample();
        saberUserExample.createCriteria().andUsernameEqualTo(username);
        List<SaberUser> userList = saberUserMapper.selectByExample(saberUserExample);
        if (!CollectionUtils.isEmpty(userList)) {
            user = userList.get(0);
            return user;
        }
        return null;
    }


}
