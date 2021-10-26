package com.xidian.qunzhi.service;

import com.xidian.qunzhi.pojo.vo.UserProjectVO;

import java.util.List;

/**
 * @author Cao Study
 * @description <h1>UserGroupService</h1>
 * @date 2021-10-22 9:26
 */
public interface ProjectGroupService {
    /**
     * 组长审批后，添加用户到项目的逻辑
     * @param projectId
     * @param userId
     * @param leaderId
     */
    void addProjectMember(Integer projectId, Integer userId, Integer leaderId);

    /**
     * 组长删除项目组成员
     * @param projectId
     * @param userId
     * @param leaderId
     */
    void deleteProjectMember(Integer projectId, Integer userId, Integer leaderId);


    /**
     * 申请成为项目组的成员
     * @param projectId
     * @param userId
     */
    void applyProjectMember(Integer projectId, Integer userId);

    /**
     * 获得项目组的所有成员
     * @param projectId
     * @param userId
     * @return
     */
    List<UserProjectVO> getProjectMember(Integer projectId, Integer userId);

    /**
     * 组长获取申请加入项目的请求
     * @param projectId
     * @param id
     */
    void getApplication(Integer projectId, Integer id);
}
