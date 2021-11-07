package com.xidian.qunzhi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xidian.qunzhi.core.enumerate.AdminOrNotEnum;
import com.xidian.qunzhi.exception.http.ForbiddenException;
import com.xidian.qunzhi.exception.http.UnAuthenticatedException;
import com.xidian.qunzhi.mapper.ProjectMapper;
import com.xidian.qunzhi.mapper.UserMapper;
import com.xidian.qunzhi.pojo.User;
import com.xidian.qunzhi.pojo.vo.StatisticVO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import com.xidian.qunzhi.service.AdminUserService;
import com.xidian.qunzhi.utils.CopyUtil;
import com.xidian.qunzhi.utils.MD5Utils;
import com.xidian.qunzhi.utils.SnowFlake;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Cao Study
 * @description <h1>AdminUserServiceImpl</h1>
 * @date 2021-10-25 21:52
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    private static final Logger LOG = LoggerFactory.getLogger(AdminUserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SnowFlake snowFlake;

    @Transactional(propagation= Propagation.SUPPORTS)
    @Override
    public UserLoginVO adminLogin(String email, String password) throws Exception {
        //1.判断帐号密码是否正确
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email", email)
                .andEqualTo("password", MD5Utils.getMD5Str(password));
        User user = userMapper.selectOneByExample(example);
        if (user == null) {
            throw new ForbiddenException(20003);
        }
        //2.判断用户是否是管理员
        if(user.getIsAdmin().intValue()!= AdminOrNotEnum.ADMIN.getValue()){
            throw new UnAuthenticatedException(20006);
        }

        //3.判断用户是否已经登录，登录了的话，删除原来的token
        if (StringUtils.isNotBlank(user.getToken())){
            redisTemplate.delete(user.getToken());
        }

        //4.生成并保存token
        Long token = snowFlake.nextId();
        //4.1 保存token(登录状态到数据库中)
        user.setToken(token.toString());
        userMapper.updateByPrimaryKeySelective(user);
        UserLoginVO userLoginVO = CopyUtil.copy(user, UserLoginVO.class);

        //4.2 保存token到redis里
        LOG.info("生成单点登录token：{}，并放入redis中", token);
        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginVO),
                60 * 30, TimeUnit.SECONDS);

        return userLoginVO;
    }

    @Override
    public StatisticVO getStatistic(UserLoginVO userLoginVO) {
        //1.判断用户是否是管理员
        if(userLoginVO.getIsAdmin().intValue()!= AdminOrNotEnum.ADMIN.getValue()){
            throw new UnAuthenticatedException(20006);
        }
        // 获取所有的key
        Set<String> keys = redisTemplate.keys("*");
        Integer onlineNum=keys.size();
        if(ObjectUtils.isEmpty(onlineNum)){
            onlineNum=0;
        }
        User user=new User();
        Integer userCount=userMapper.selectCount(user);
        Integer projectCount=projectMapper.getCount();
        StatisticVO statisticVO=new StatisticVO();
        statisticVO.setUserCount(userCount);
        statisticVO.setProjectCount(projectCount);
        statisticVO.setOnlineNum(onlineNum);
        return statisticVO;
    }
}
