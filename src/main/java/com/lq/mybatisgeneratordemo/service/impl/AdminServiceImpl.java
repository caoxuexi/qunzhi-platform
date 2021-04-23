package com.lq.mybatisgeneratordemo.service.impl;

import com.github.pagehelper.PageHelper;
import com.lq.mybatisgeneratordemo.dto.SaberUserParam;
import com.lq.mybatisgeneratordemo.mbg.mapper.SaberAdminMapper;
import com.lq.mybatisgeneratordemo.mbg.mapper.SaberUserMapper;
import com.lq.mybatisgeneratordemo.mbg.model.SaberAdmin;
import com.lq.mybatisgeneratordemo.mbg.model.SaberAdminExample;
import com.lq.mybatisgeneratordemo.mbg.model.SaberUser;
import com.lq.mybatisgeneratordemo.mbg.model.SaberUserExample;
import com.lq.mybatisgeneratordemo.service.AdminService;
import io.micrometer.core.instrument.Timer.Sample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private SaberAdminMapper saberAdminMapper;
    @Autowired
    private SaberUserMapper saberUserMapper;

    @Override
    public boolean login(String username, String password) {
        SaberAdmin admin = getAdminByUsername(username);
        return admin.getPassword().equals(password);
    }

    @Override
    public SaberAdmin getAdminByUsername(String username) {
        SaberAdmin admin = null;
        SaberAdminExample saberAdminExample = new SaberAdminExample();
        saberAdminExample.createCriteria().andUsernameEqualTo(username);
        List<SaberAdmin> adminList = saberAdminMapper.selectByExample(saberAdminExample);
        if(adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
        }
        return admin;
    }

    @Override
    public List<SaberUser> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SaberUserExample example = new SaberUserExample();
        SaberUserExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andUsernameLike("%" + keyword + "%");
        }
        return saberUserMapper.selectByExample(example);
    }

    @Override
    public SaberUser getUser(Integer id) {
        return saberUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Integer id, SaberUserParam userParam) {
        SaberUser saberUser = new SaberUser();
        BeanUtils.copyProperties(userParam, saberUser);
        saberUser.setId(id);
        return saberUserMapper.updateByPrimaryKeySelective(saberUser);
    }
}
