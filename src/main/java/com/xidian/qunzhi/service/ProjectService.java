package com.xidian.qunzhi.service;

import com.xidian.qunzhi.pojo.basic.PageVO;
import com.xidian.qunzhi.pojo.dto.SearchProjectDTO;
import com.xidian.qunzhi.pojo.vo.ProjectAdminVO;
import com.xidian.qunzhi.pojo.vo.ProjectDetailVO;
import com.xidian.qunzhi.pojo.vo.ProjectPreviewVO;
import com.xidian.qunzhi.pojo.vo.UserLoginVO;

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

}
