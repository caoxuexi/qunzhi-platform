package com.xidian.qunzhi.service.impl;

import com.xidian.qunzhi.core.enumerate.UserRoleEnum;
import com.xidian.qunzhi.exception.http.UnAuthenticatedException;
import com.xidian.qunzhi.mapper.UserProjectMapper;
import com.xidian.qunzhi.pojo.User;
import com.xidian.qunzhi.pojo.UserProject;
import com.xidian.qunzhi.service.ProjectGroupService;
import com.xidian.qunzhi.service.ProjectService;
import com.xidian.qunzhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

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

    /**
     * 确认当前用户是否为组长
     */
    private void checkLeader(Integer projectId,Integer leaderId){
        //判断该项目是否属于当前用户
        UserProject checkUserProject = projectService.checkBelonging(projectId, leaderId);
        //判断当前用户是否是该项目的组长，只有组长才能添加组员到项目
        if(checkUserProject.getUserRole()!=1){
            throw new UnAuthenticatedException(30003);
        }
    }

    @Override
    public void applyProjectMember(Integer projectId, Integer userId) {
        //TODO
    }

    @Override
    public void getApplication(Integer userId) {
        //TODO
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addProjectMember(Integer projectId, Integer userId, Integer leaderId) {
        checkLeader(projectId,leaderId);
        //向表中插入关系数据
        UserProject userProject=new UserProject();
        userProject.setUserId(userId);
        userProject.setProjectId(projectId);
        userProject.setUserRole(UserRoleEnum.MEMBER.getValue().shortValue());
        userProjectMapper.insertSelective(userProject);
    }

    @Override
    public void deleteProjectMember(Integer projectId, Integer userId, Integer leaderId) {
        checkLeader(projectId,leaderId);
        UserProject userProject=new UserProject();
        userProject.setDeleteTime(new Date());
        Example example=new Example(UserProject.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("projectId",projectId);
        userProjectMapper.updateByExampleSelective(userProject,example);
    }
}
