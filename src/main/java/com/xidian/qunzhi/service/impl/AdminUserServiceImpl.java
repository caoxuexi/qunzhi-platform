package com.xidian.qunzhi.service.impl;

import com.xidian.qunzhi.core.enumerate.AdminOrNotEnum;
import com.xidian.qunzhi.core.enumerate.IsLoginEnum;
import com.xidian.qunzhi.exception.http.ForbiddenException;
import com.xidian.qunzhi.exception.http.UnAuthenticatedException;
import com.xidian.qunzhi.mapper.UserMapper;
import com.xidian.qunzhi.pojo.User;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import com.xidian.qunzhi.service.AdminUserService;
import com.xidian.qunzhi.utils.CopyUtil;
import com.xidian.qunzhi.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Cao Study
 * @description <h1>AdminUserServiceImpl</h1>
 * @date 2021-10-25 21:52
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation= Propagation.SUPPORTS)
    @Override
    public UserLoginVO adminLogin(String email, String password) throws Exception {
        //判断帐号密码是否正确
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email", email)
                .andEqualTo("password", MD5Utils.getMD5Str(password));
        User user = userMapper.selectOneByExample(example);
        if (user == null) {
            throw new ForbiddenException(20003);
        }
        //判断用户是否是管理员
        if(user.getIsAdmin().intValue()!= AdminOrNotEnum.ADMIN.getValue()){
            throw new UnAuthenticatedException(20006);
        }
        //判断用户是否已经登录
        if (user.getIsLogin()!= IsLoginEnum.ALREADY_LOGIN.getValue().shortValue()){
            throw new ForbiddenException(20009);
        }
        UserLoginVO userLoginVO = CopyUtil.copy(user, UserLoginVO.class);
        user.setIsLogin((short) 1);
        userMapper.updateByPrimaryKeySelective(user);
        return userLoginVO;
    }
}
