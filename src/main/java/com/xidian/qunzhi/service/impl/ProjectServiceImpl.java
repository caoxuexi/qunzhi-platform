package com.xidian.qunzhi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xidian.qunzhi.exception.http.ForbiddenException;
import com.xidian.qunzhi.exception.http.NotFoundException;
import com.xidian.qunzhi.exception.http.UnAuthenticatedException;
import com.xidian.qunzhi.mapper.DeviceLogMapper;
import com.xidian.qunzhi.mapper.ProjectMapper;
import com.xidian.qunzhi.mapper.UserMapper;
import com.xidian.qunzhi.mapper.UserProjectMapper;
import com.xidian.qunzhi.pojo.DeviceLog;
import com.xidian.qunzhi.pojo.Project;
import com.xidian.qunzhi.pojo.UserProject;
import com.xidian.qunzhi.pojo.basic.PageVO;
import com.xidian.qunzhi.pojo.dto.ProjectDTO;
import com.xidian.qunzhi.pojo.dto.SearchProjectDTO;
import com.xidian.qunzhi.pojo.vo.*;
import com.xidian.qunzhi.service.ProjectService;
import com.xidian.qunzhi.utils.CopyUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author Cao Study
 * @description <h1>ProductServiceImpl</h1>
 * @date 2021-10-18 16:36
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserProjectMapper userProjectMapper;

    @Autowired
    private DeviceLogMapper deviceLogMapper;

    private static final Logger LOG= LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ProjectPreviewVO> listAllByUser(UserLoginVO userLoginVO) {
        List<Project> productList= projectMapper.listAllByUserId(userLoginVO.getId());
        List<ProjectPreviewVO> productPreviewVOList = CopyUtil.copyList(productList, ProjectPreviewVO.class);
        return productPreviewVOList;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PageVO<ProjectAdminVO> searchByAdmin(SearchProjectDTO searchProjectDTO, UserLoginVO userLoginVO) {
        //先检查用户是否是管理员
        Short isAdmin=userLoginVO.getIsAdmin();
        if(isAdmin!=1){
            throw new UnAuthenticatedException(30004);
        }
        //条件查询
        Example example=new Example(Project.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("delete_time is null");
        //按项目名查询
        if(!StringUtils.isEmpty(searchProjectDTO.getName())){
            criteria.andLike("name","%"+searchProjectDTO.getName()+"%");
        }

        PageHelper.startPage(searchProjectDTO.getPage(), searchProjectDTO.getSize());
        List<Project> projectList = projectMapper.selectByExample(example);
        PageInfo<Project> pageInfo = new PageInfo<>(projectList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());
        List<ProjectAdminVO> projectAdminVOList = CopyUtil.copyList(projectList, ProjectAdminVO.class);
        PageVO<ProjectAdminVO> pageVO = new PageVO<>();
        pageVO.setTotal(pageInfo.getTotal());
        pageVO.setList(projectAdminVOList);
        return pageVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public UserProject checkBelonging(Integer projectId, Integer usrId) {
        Example example=new Example(UserProject.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",projectId)
                .andEqualTo("userId",usrId)
                .andCondition("delete_time is null");

        UserProject userProject = userProjectMapper.selectOneByExample(example);
        if(userProject==null) {
            //如果该项目不属于当前用户则抛出异常
            throw new ForbiddenException(30002);
        }
        return userProject;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ProjectDetailVO detail(Integer projectId, Integer userId) {
        //判断该项目是否属于当前用户
        checkBelonging(projectId,userId);
        Example example=new Example(Project.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",projectId)
                .andCondition("delete_time is null");

        Project project = projectMapper.selectOneByExample(example);
        if(ObjectUtils.isEmpty(project)){
            //抛出项目不存在异常消息
            throw new NotFoundException(30001);
        }
        ProjectDetailVO productDetailVO = CopyUtil.copy(project, ProjectDetailVO.class);
        return productDetailVO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ProjectDetailVO create(ProjectDTO projectDTO, Integer id) {
        //TODO 创建项目
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void delete(Integer projectId, Integer userId) {
        //判断该项目是否属于当前用户
        UserProject checkUserProject = checkBelonging(projectId, userId);
        //判断当前用户是否是该项目的组长，只有组长才能删除项目
        if(checkUserProject.getUserRole()!=1){
            throw new UnAuthenticatedException(30003);
        }
        //删除所有用户和该项目的关系,更新delete_time字段
        Example deleteExample=new Example(UserProject.class);
        Example.Criteria deleteCriteria = deleteExample.createCriteria();
        deleteCriteria.andEqualTo("projectId",projectId);
        UserProject userProject=new UserProject();
        userProject.setDeleteTime(new Date());
        userProjectMapper.updateByExampleSelective(userProject,deleteExample);

        //更新项目的delete_time字段，设置时间即为删除
        Example deleteProjectExample=new Example(Project.class);
        deleteProjectExample.createCriteria().andEqualTo("id",projectId);
        Project project=new Project();
        project.setDeleteTime(new Date());
        projectMapper.updateByExampleSelective(project,deleteProjectExample);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<DeviceLogVO> logs(Integer projectId, Integer userId) {
        //判断该项目是否属于当前用户
        checkBelonging(projectId,userId);
        Example example=new Example(DeviceLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", projectId);

        List<DeviceLog> deviceLogList = deviceLogMapper.selectByExample(example);
        if(deviceLogList.size()<=0){
            //抛出项目不存在异常消息
            throw new NotFoundException(30005);
        }
        List<DeviceLogVO> deviceLogVOList = CopyUtil.copyList(deviceLogList, DeviceLogVO.class);
        return deviceLogVOList;
    }
}
