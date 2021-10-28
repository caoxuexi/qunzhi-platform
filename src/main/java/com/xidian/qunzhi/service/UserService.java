package com.xidian.qunzhi.service;


import com.xidian.qunzhi.pojo.basic.PageVO;
import com.xidian.qunzhi.pojo.dto.*;
import com.xidian.qunzhi.pojo.vo.ProjectAdminVO;
import com.xidian.qunzhi.pojo.vo.UserAdminVO;
import com.xidian.qunzhi.pojo.vo.UserInformationVO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;

import javax.validation.Valid;

public interface UserService {

    /**
     * 修改密码
     * @param changePasswordDTO
     * @param userId
     */
    void changePassword(ChangePasswordDTO changePasswordDTO, Integer userId) throws Exception;

    /**
     * 开发者注册
     */
    boolean register(UserRegistDTO userRegistDTO, String password);


    /**
     * 普通用户登录
     * @param email
     * @param password
     * @return 登录用户VO
     * @throws Exception
     */
    UserLoginVO login(String email, String password) throws Exception;

    /**
     * 修改用户具体信息
     * @param userInformationDTO
     * @param userId
     */
    UserInformationVO changeInformation(UserInformationDTO userInformationDTO, Integer userId);

    /**
     * 获取用户具体信息
     * @param userId
     * @return
     */
    UserInformationVO getInformation(Integer userId);

    /**
     * 用户退出
     * @param userId
     * @return
     */
    void logout(Integer userId);

    /**
     * 管理员获取所有用户的信息
     * @param searchProjectDTO
     * @param userLoginVO
     * @return
     */
    PageVO<UserAdminVO> searchByAdmin(@Valid SearchUserDTO searchProjectDTO, UserLoginVO userLoginVO);


}
