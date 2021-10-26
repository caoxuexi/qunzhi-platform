package com.xidian.qunzhi.service.impl;

import com.xidian.qunzhi.core.enumerate.UserRoleEnum;
import com.xidian.qunzhi.exception.http.ForbiddenException;
import com.xidian.qunzhi.exception.http.UnAuthenticatedException;
import com.xidian.qunzhi.mapper.UserMapper;
import com.xidian.qunzhi.mapper.UserProjectMapper;
import com.xidian.qunzhi.pojo.User;
import com.xidian.qunzhi.pojo.UserProject;
import com.xidian.qunzhi.pojo.vo.UserProjectVO;
import com.xidian.qunzhi.service.ProjectGroupService;
import com.xidian.qunzhi.service.ProjectService;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Cao Study
 * @description <h1>UserGroupServiceImpl</h1>
 * @date 2021-10-22 9:27
 */

@Service
public class ProjectGroupServiceImpl implements ProjectGroupService {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserProjectMapper userProjectMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 确认当前用户是否为组长
     */
    private UserProject checkLeader(Integer projectId, Integer leaderId) {
        //判断该项目是否属于当前用户
        UserProject checkUserProject = projectService.checkBelonging(projectId, leaderId);
        //判断当前用户是否是该项目的组长，只有组长才能添加组员到项目
        if (checkUserProject.getUserRole() != 1) {
            throw new UnAuthenticatedException(30003);
        }
        return checkUserProject;
    }

    @Override
    public void applyProjectMember(Integer projectId, Integer userId) {
        //TODO 添加项目成员

    }

    @Override
    public List<UserProjectVO> getProjectMember(Integer projectId, Integer userId) {
        //判断该项目是否属于当前用户
        UserProject checkUserProject = projectService.checkBelonging(projectId, userId);
        Example example = new Example(UserProject.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId", projectId);
        List<UserProject> userProjects = userProjectMapper.selectByExample(example);
        List<UserProjectVO> userProjectVOList = new ArrayList<>();
        for (UserProject userProject : userProjects) {
            UserProjectVO userProjectVO = new UserProjectVO();
            userProjectVO.setUserId(userProject.getUserId());
            ;
            userProjectVO.setUserNickname(userProject.getUserNickname());
            userProjectVO.setUserRealname(userProject.getUserRealname());
            if (userProject.getUserRole() == UserRoleEnum.LEADER.getValue().shortValue()) {
                userProjectVO.setUserRole(UserRoleEnum.LEADER.getDescription());
            } else {
                userProjectVO.setUserRole(UserRoleEnum.MEMBER.getDescription());
            }
            userProjectVOList.add(userProjectVO);
        }
        return userProjectVOList;
    }

    @Override
    public void getApplication(Integer projectId, Integer id) {
        //TODO 获取项目组请求
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addProjectMember(Integer projectId, Integer userId, Integer leaderId) {
        checkLeader(projectId, leaderId);
        //查询用户是否已经属于该项目了
        Example example = new Example(UserProject.class);
        example.createCriteria().andEqualTo("userId", userId)
                .andEqualTo("projectId", projectId);
        UserProject existUserProject = userProjectMapper.selectOneByExample(example);
        if (ObjectUtils.isNotEmpty(existUserProject)){
            throw new ForbiddenException(40002);
        }
        //向表中插入关系数据
        UserProject userProject = new UserProject();
        userProject.setUserId(userId);
        userProject.setProjectId(projectId);
        userProject.setUserRole(UserRoleEnum.MEMBER.getValue().shortValue());
        //查询出用户的真实姓名和昵称，插入表
        User user = userMapper.selectByPrimaryKey(userId);
        if(ObjectUtils.isEmpty(user)){
            throw new ForbiddenException(20002);
        }
        userProject.setUserNickname(user.getNickname());
        userProject.setUserRealname(user.getRealname());
        userProjectMapper.insertSelective(userProject);
    }

    @Override
    public void deleteProjectMember(Integer projectId, Integer userId, Integer leaderId) {
        checkLeader(projectId, leaderId);
        UserProject userProject = new UserProject();
        userProject.setDeleteTime(new Date());
        Example example = new Example(UserProject.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("projectId", projectId);
        userProjectMapper.updateByExampleSelective(userProject, example);
    }
}
