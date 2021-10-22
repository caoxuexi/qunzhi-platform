package com.xidian.qunzhi.service;

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

    void getApplication(Integer userId);

    void applyProjectMember(Integer projectId, Integer userId);
}
