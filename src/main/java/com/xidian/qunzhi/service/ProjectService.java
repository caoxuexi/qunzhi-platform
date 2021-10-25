package com.xidian.qunzhi.service;

import com.xidian.qunzhi.pojo.UserProject;
import com.xidian.qunzhi.pojo.basic.PageVO;
import com.xidian.qunzhi.pojo.dto.ProjectDTO;
import com.xidian.qunzhi.pojo.dto.SearchProjectDTO;
import com.xidian.qunzhi.pojo.vo.*;

import java.util.List;

/**
 * @author Cao Study
 * @description <h1>ProductService</h1>
 * @date 2021-10-18 16:36
 */
public interface ProjectService {

    /**
     * 获的当前用户所有的项目的缩略图
     *
     * @param userLoginVO
     * @return
     */
    List<ProjectPreviewVO> listAllByUser(UserLoginVO userLoginVO);

    /**
     * 管理员分页查询所有的项目
     * @param searchProjectDTO
     * @param userLoginVO
     * @return
     */
    PageVO<ProjectAdminVO> searchByAdmin(SearchProjectDTO searchProjectDTO, UserLoginVO userLoginVO);

    /**
     * 检查项目是否属于当前用户
     * @param projectId
     * @param usrId
     * @return
     */
    public UserProject checkBelonging(Integer projectId, Integer usrId);

    /**
     * 根据项目名称搜索项目的详细信息
     *
     * @param projectId
     * @param userId
     * @return
     */
    ProjectDetailVO detail(Integer projectId, Integer userId);

    /**
     * 删除项目(必须是组长身份才行)
     *
     * @param projectId
     * @return
     */
    void delete(Integer projectId, Integer userId);

    /**
     * 新建项目
     * @param projectDTO
     * @param id
     * @return
     */
    ProjectDetailVO create(ProjectDTO projectDTO, Integer id);

    /**
     * 根据项目名称搜索项目的日志信息
     *
     * @param projectId
     * @param userId
     * @return
     */
    List<DeviceLogVO> logs(Integer projectId, Integer userId);
}

