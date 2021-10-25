package com.xidian.qunzhi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xidian.qunzhi.core.enumerate.IsLoginEnum;
import com.xidian.qunzhi.exception.http.ForbiddenException;
import com.xidian.qunzhi.exception.http.UnAuthenticatedException;
import com.xidian.qunzhi.mapper.UserMapper;

import com.xidian.qunzhi.pojo.Project;
import com.xidian.qunzhi.pojo.basic.PageVO;
import com.xidian.qunzhi.pojo.dto.ChangePasswordDTO;
import com.xidian.qunzhi.pojo.dto.SearchUserDTO;
import com.xidian.qunzhi.pojo.dto.UserInformationDTO;
import com.xidian.qunzhi.pojo.dto.UserRegistDTO;
import com.xidian.qunzhi.pojo.vo.ProjectAdminVO;
import com.xidian.qunzhi.pojo.vo.UserAdminVO;
import com.xidian.qunzhi.pojo.vo.UserInformationVO;
import com.xidian.qunzhi.service.UserService;
import com.xidian.qunzhi.utils.CopyUtil;
import com.xidian.qunzhi.utils.MD5Utils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void changePassword(ChangePasswordDTO changePasswordDTO, Integer userId) throws Exception {
        String rawOldPassword = changePasswordDTO.getOldPassword();
        String oldPassword = MD5Utils.getMD5Str(rawOldPassword);

        //查询旧密码是否输入正确
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("password",oldPassword)
                .andEqualTo("id",userId);
        User user=userMapper.selectOneByExample(example);
        if(ObjectUtils.isEmpty(user)){
            throw new ForbiddenException(20007);
        }
        //判断两次新密码是否一致
        if(!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getReNewPassword())){
            throw new ForbiddenException(20008);
        }
        User newUser=new User();
        newUser.setId(userId);
        newUser.setPassword(MD5Utils.getMD5Str(changePasswordDTO.getNewPassword()));
        //修改密码
        userMapper.updateByPrimaryKeySelective(newUser);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean register(UserRegistDTO userRegistDTO, String password) {
        //查询是否有相同用户名的用户
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

        LOG.info("用户" + user.toString());
        int count = userMapper.insertSelective(user);
        return count != 0;
    }

    @Transactional(propagation=Propagation.SUPPORTS)
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
        //判断用户是否已经登录
        if (user.getIsLogin()!= IsLoginEnum.ALREADY_LOGIN.getValue().shortValue()){
            throw new ForbiddenException(20009);
        }
        UserLoginVO userLoginVO = CopyUtil.copy(user, UserLoginVO.class);
        user.setIsLogin((short) 1);
        userMapper.updateByPrimaryKeySelective(user);
        return userLoginVO;
    }

    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public UserInformationVO changeInformation(UserInformationDTO userInformationDTO, Integer userId) {
        //设置更新
        User user=new User();
        //设置主键，按照主键进行数据更新
        user.setId(userId);
        //根据传入参数设置属性值
        user.setNickname(userInformationDTO.getNickname());
        if(StringUtils.isNotBlank(userInformationDTO.getAddress())){
            user.setAddress(userInformationDTO.getAddress());
        }
        if(StringUtils.isNotBlank(userInformationDTO.getDistrict())){
            user.setDistrict(userInformationDTO.getDistrict());
        }
        if(StringUtils.isNotBlank(userInformationDTO.getRealname())){
            user.setRealname(userInformationDTO.getRealname());
        }
        if(StringUtils.isNotBlank(userInformationDTO.getQq())){
            user.setQq(userInformationDTO.getQq());
        }
        if(StringUtils.isNotBlank(userInformationDTO.getMobile())){
            user.setMobile(userInformationDTO.getMobile());
        }
        userMapper.updateByPrimaryKeySelective(user);
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
        User user=new User();
        user.setId(userId);
        user.setIsLogin((short)0);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public PageVO<UserAdminVO> searchByAdmin(SearchUserDTO searchUserDTO, UserLoginVO userLoginVO) {
        //先检查用户是否是管理员
        Short isAdmin=userLoginVO.getIsAdmin();
        if(isAdmin!=1){
            throw new UnAuthenticatedException(30004);
        }
        //条件查询
        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        //按昵称查询
        if(!StringUtils.isEmpty(searchUserDTO.getNickname())){
            criteria.andLike("nickname","%"+searchUserDTO.getNickname()+"%");
        }
        //按真名查询
        if(!StringUtils.isEmpty(searchUserDTO.getNickname())){
            criteria.andLike("realname","%"+searchUserDTO.getRealname()+"%");
        }
        //按邮箱(帐号)查询
        if(!StringUtils.isEmpty(searchUserDTO.getEmail())){
            criteria.andLike("email","%"+searchUserDTO.getEmail()+"%");
        }

        PageHelper.startPage(searchUserDTO.getPage(), searchUserDTO.getSize());
        List<User> userList = userMapper.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());
        List<UserAdminVO> userAdminVOList = CopyUtil.copyList(userList , UserAdminVO.class);
        PageVO<UserAdminVO> pageVO = new PageVO<>();
        pageVO.setTotal(pageInfo.getTotal());
        pageVO.setList( userAdminVOList);
        return pageVO;
    }
}
