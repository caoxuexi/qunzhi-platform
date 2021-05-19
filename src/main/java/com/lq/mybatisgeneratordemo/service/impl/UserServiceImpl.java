package com.lq.mybatisgeneratordemo.service.impl;

import com.lq.mybatisgeneratordemo.common.utils.JwtTokenUtil;
import com.lq.mybatisgeneratordemo.domain.SaberUserDetails;
import com.lq.mybatisgeneratordemo.dto.SaberUserParam;
import com.lq.mybatisgeneratordemo.dto.UserLoginParam;
import com.lq.mybatisgeneratordemo.mbg.mapper.SaberUserMapper;
import com.lq.mybatisgeneratordemo.mbg.model.SaberUser;
import com.lq.mybatisgeneratordemo.mbg.model.SaberUserExample;
import com.lq.mybatisgeneratordemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
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
        String encodePassword = passwordEncoder.encode(password);
        saberUser.setPassword(encodePassword);
        saberUserMapper.insert(saberUser);
        saberUser.setPassword(null);
        return true;
    }

    @Override
    public SaberUser getCurrentUser() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        SaberUserDetails saberUserDetails = (SaberUserDetails) auth.getPrincipal();
        return saberUserDetails.getSaberUser();
    }

    @Override
    public int update(SaberUserParam saberUserParam) {
        return 0;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails =loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        }catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
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

    @Override
    public boolean updatePassword(String password) {
        SaberUser saberUser = getCurrentUser();
        saberUser.setPassword(passwordEncoder.encode(password));
        saberUserMapper.updateByPrimaryKeySelective(saberUser);
        return true;
    }

    public UserDetails loadUserByUsername(String username) {
        SaberUser user = getByUsername(username);
        if(user!=null){
            return new SaberUserDetails(user);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
