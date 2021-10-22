package com.xidian.qunzhi.service;


import com.xidian.qunzhi.pojo.dto.ChangePasswordDTO;
import com.xidian.qunzhi.pojo.dto.UserInformationDTO;
import com.xidian.qunzhi.pojo.dto.UserRegistDTO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
     * 管理员登录
     * @param email
     * @param password
     * @return 登录用户VO
     */
    UserLoginVO adminLogin(String email, String password) throws Exception;

    /**
     * 修改用户信息
     * @param userInformationDTO
     * @param userLoginVO
     */
    void changeInformation(UserInformationDTO userInformationDTO, UserLoginVO userLoginVO);
}
