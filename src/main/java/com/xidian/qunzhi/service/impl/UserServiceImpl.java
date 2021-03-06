package com.xidian.qunzhi.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xidian.qunzhi.exception.http.ForbiddenException;
import com.xidian.qunzhi.exception.http.UnAuthenticatedException;
import com.xidian.qunzhi.mapper.UserMapper;

import com.xidian.qunzhi.mapper.UserProjectMapper;
import com.xidian.qunzhi.pojo.UserProject;
import com.xidian.qunzhi.pojo.basic.PageVO;
import com.xidian.qunzhi.pojo.dto.ChangePasswordDTO;
import com.xidian.qunzhi.pojo.dto.SearchUserDTO;
import com.xidian.qunzhi.pojo.dto.UserInformationDTO;
import com.xidian.qunzhi.pojo.dto.UserRegistDTO;
import com.xidian.qunzhi.pojo.vo.UserAdminVO;
import com.xidian.qunzhi.pojo.vo.UserInformationVO;
import com.xidian.qunzhi.service.UserService;
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
import com.xidian.qunzhi.pojo.User;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.genid.GenId;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserProjectMapper userProjectMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SnowFlake snowFlake;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO, Integer userId) throws Exception {
        String rawOldPassword = changePasswordDTO.getOldPassword();
        String oldPassword = MD5Utils.getMD5Str(rawOldPassword);

        //?????????????????????????????????
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("password",oldPassword)
                .andEqualTo("id",userId);
        User user=userMapper.selectOneByExample(example);
        if(ObjectUtils.isEmpty(user)){
            throw new ForbiddenException(20007);
        }
        //?????????????????????????????????
        if(!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getReNewPassword())){
            throw new ForbiddenException(20008);
        }
        User newUser=new User();
        newUser.setId(userId);
        newUser.setPassword(MD5Utils.getMD5Str(changePasswordDTO.getNewPassword()));
        //????????????
        userMapper.updateByPrimaryKeySelective(newUser);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean register(UserRegistDTO userRegistDTO, String password) {
        //???????????????????????????????????????
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email", userRegistDTO.getEmail());
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() > 0) {
            throw new ForbiddenException(20001);
        }
        User user = new User();
        user.setPassword(password);
        user.setEmail(userRegistDTO.getEmail());
        user.setNickname(userRegistDTO.getNickname());
        user.setRealname(userRegistDTO.getRealname());

        LOG.info("??????" + user.toString());
        int count = userMapper.insertSelective(user);
        return count != 0;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public UserLoginVO login(String email, String password) throws Exception {
        //1.??????????????????????????????
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("email", email)
                .andEqualTo("password", MD5Utils.getMD5Str(password));
        User user = userMapper.selectOneByExample(example);
        if (user == null) {
            throw new ForbiddenException(20003);
        }
        //2.??????????????????????????????????????????????????????????????????token
        if (StringUtils.isNotBlank(user.getToken())){
            redisTemplate.delete(user.getToken());
        }

        //4.???????????????token
        Long token = snowFlake.nextId();
        //4.1 ??????token(???????????????????????????)
        user.setToken(token.toString());
        userMapper.updateByPrimaryKeySelective(user);
        UserLoginVO userLoginVO = CopyUtil.copy(user, UserLoginVO.class);

        //4.2 ??????token???redis???
        LOG.info("??????????????????token???{}????????????redis???", token);
        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginVO),
                60 * 30, TimeUnit.SECONDS);

        return userLoginVO;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public UserInformationVO changeInformation(UserInformationDTO userInformationDTO, Integer userId) {
        //????????????
        User user=new User();
        UserProject userProject=new UserProject();
        //?????????????????????????????????????????????,??????UserProject?????????Example
        user.setId(userId);
        Example upexample=new Example(UserProject.class);
        upexample.createCriteria().andEqualTo("userId",userId);
        //?????????????????????????????????(?????????)
        user.setNickname(userInformationDTO.getNickname());
        userProject.setUserNickname(userInformationDTO.getNickname());
        user.setRealname(userInformationDTO.getRealname());
        userProject.setUserRealname(userInformationDTO.getRealname());

        //?????????????????????????????????(?????????)
        if(StringUtils.isNotBlank(userInformationDTO.getAddress())){
            user.setAddress(userInformationDTO.getAddress());
        }
        if(StringUtils.isNotBlank(userInformationDTO.getDistrict())){
            user.setDistrict(userInformationDTO.getDistrict());
        }
        if(StringUtils.isNotBlank(userInformationDTO.getQq())){
            user.setQq(userInformationDTO.getQq());
        }
        if(StringUtils.isNotBlank(userInformationDTO.getMobile())){
            user.setMobile(userInformationDTO.getMobile());
        }


        //????????????
        userMapper.updateByPrimaryKeySelective(user);
        userProjectMapper.updateByExampleSelective(userProject,upexample);

        User newUser=userMapper.selectByPrimaryKey(userId);
        UserInformationVO userInformationVO = CopyUtil.copy(newUser, UserInformationVO.class);
        return userInformationVO;
    }

    @Transactional(propagation=Propagation.SUPPORTS)
    @Override
    public UserInformationVO getInformation(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        UserInformationVO userInformationVO = CopyUtil.copy(user, UserInformationVO.class);
        return userInformationVO;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public void logout(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        String token=user.getToken();
        redisTemplate.delete(token);
        //??????token
        user.setToken("");
        LOG.info("???redis?????????token: {}", token);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public PageVO<UserAdminVO> searchByAdmin(SearchUserDTO searchUserDTO, UserLoginVO userLoginVO) {
        //?????????????????????????????????
        Short isAdmin=userLoginVO.getIsAdmin();
        if(isAdmin!=1){
            throw new UnAuthenticatedException(30004);
        }
        //????????????
        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        //???????????????
        if(StringUtils.isNotBlank(searchUserDTO.getNickname())){
            criteria.andLike("nickname","%"+searchUserDTO.getNickname()+"%");
        }
        //???????????????
        if(StringUtils.isNotBlank(searchUserDTO.getRealname())){
            criteria.andLike("realname","%"+searchUserDTO.getRealname()+"%");
        }
        //?????????(??????)??????
        if(StringUtils.isNotBlank(searchUserDTO.getEmail())){
            criteria.andLike("email","%"+searchUserDTO.getEmail()+"%");
        }

        PageHelper.startPage(searchUserDTO.getPage(), searchUserDTO.getSize());
        List<User> userList = userMapper.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        LOG.info("????????????{}", pageInfo.getTotal());
        LOG.info("????????????{}", pageInfo.getPages());
        List<UserAdminVO> userAdminVOList = CopyUtil.copyList(userList , UserAdminVO.class);
        PageVO<UserAdminVO> pageVO = new PageVO<>();
        pageVO.setTotal(pageInfo.getTotal());
        pageVO.setList( userAdminVOList);
        pageVO.setPage(searchUserDTO.getPage());
        return pageVO;
    }

}
