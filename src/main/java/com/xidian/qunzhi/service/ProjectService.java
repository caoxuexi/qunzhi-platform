package com.xidian.qunzhi.service;

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
     * @param userLoginVO
     * @return
     */
    List<ProjectPreviewVO> listAll(UserLoginVO userLoginVO);

    /**
     * 判断项目是否属于用户
     * @param projectId
     * @param userId
     */
    void checkBelonging(Integer projectId, Integer userId);

    /**
     * 根据项目名称搜索项目的详细信息
     * @param projectId
     * @return
     */
    ProjectDetailVO detail(Integer projectId);
}
