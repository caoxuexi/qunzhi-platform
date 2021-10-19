package com.xidian.qunzhi.service.impl;

import com.xidian.qunzhi.exception.http.ForbiddenException;
import com.xidian.qunzhi.mapper.ProjectMapper;
import com.xidian.qunzhi.mapper.UserMapper;
import com.xidian.qunzhi.mapper.UserProjectMapper;
import com.xidian.qunzhi.pojo.Project;
import com.xidian.qunzhi.pojo.UserProject;
import com.xidian.qunzhi.pojo.vo.ProjectDetailVO;
import com.xidian.qunzhi.pojo.vo.ProjectPreviewVO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;
import com.xidian.qunzhi.service.ProjectService;
import com.xidian.qunzhi.utils.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    public List<ProjectPreviewVO> listAll(UserLoginVO userLoginVO) {
        List<Project> productList= projectMapper.listAllByUserId(userLoginVO.getId());
        List<ProjectPreviewVO> productPreviewVOList = CopyUtil.copyList(productList, ProjectPreviewVO.class);
        return productPreviewVOList;
    }

    @Override
    public void checkBelonging(Integer projectId, Integer usrId) {
        Example example=new Example(UserProject.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("projectId",projectId)
                .andEqualTo("userId",usrId);

        UserProject userProject = userProjectMapper.selectOneByExample(example);
        if(userProject==null) {
            //如果该项目不属于当前用户则抛出异常
            throw new ForbiddenException(30002);
        }
    }

    @Override
    public ProjectDetailVO detail(Integer projectId) {
        Example example=new Example(UserProject.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",projectId)
                .andEqualTo("deleteTime",null);
        Project product = projectMapper.selectOneByExample(example);
        ProjectDetailVO productDetailVO = CopyUtil.copy(product, ProjectDetailVO.class);
        return productDetailVO;
    }


}
