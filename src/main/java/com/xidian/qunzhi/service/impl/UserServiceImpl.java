package com.xidian.qunzhi.service.impl;

import com.xidian.qunzhi.core.enumerate.AdminOrNotEnum;
import com.xidian.qunzhi.exception.http.ForbiddenException;
import com.xidian.qunzhi.exception.http.UnAuthenticatedException;
import com.xidian.qunzhi.mapper.UserMapper;

import com.xidian.qunzhi.service.UserService;
import com.xidian.qunzhi.utils.CopyUtil;
import com.xidian.qunzhi.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.xidian.qunzhi.pojo.User;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean register(String email, String password) {
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        user.setNickname("default");
        //查询是否有相同用户名的用户
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email", email);
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() > 0) {
            throw new ForbiddenException(20001);
        }
        LOGGER.info("用户" + user.toString());
        int count = userMapper.insertSelective(user);
        return count != 0;
    }

    @Override
    public UserLoginVO login(String email, String password) throws Exception {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email", email)
                .andEqualTo("password", MD5Utils.getMD5Str(password));
        User user = userMapper.selectOneByExample(example);
        if (user == null) {
            throw new ForbiddenException(20003);
        }
        UserLoginVO userLoginVO = CopyUtil.copy(user, UserLoginVO.class);
        return userLoginVO;
    }

    @Override
    public UserLoginVO adminLogin(String email, String password) throws Exception {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email", email)
                .andEqualTo("password", MD5Utils.getMD5Str(password));
        User user = userMapper.selectOneByExample(example);
        if (user == null) {
            throw new ForbiddenException(20003);
        }
         //判断用户是否是管理员
        if(user.getIsAdmin().intValue()!=AdminOrNotEnum.ADMIN.getValue()){
            throw new UnAuthenticatedException(20006);
        }
        UserLoginVO userLoginVO = CopyUtil.copy(user, UserLoginVO.class);
        return userLoginVO;
    }
}
