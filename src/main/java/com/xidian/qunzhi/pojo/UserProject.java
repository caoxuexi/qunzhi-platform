package com.xidian.qunzhi.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_project")
public class UserProject {
    @Id
    private Integer id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "project_id")
    private Integer projectId;

    /**
     * 用户对于项目的角色：1.组长  2.组员
     */
    @Column(name = "user_role")
    private Short userRole;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_realname")
    private String userRealname;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "delete_time")
    private Date deleteTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return project_id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取用户对于项目的角色：1.组长  2.组员
     *
     * @return user_role - 用户对于项目的角色：1.组长  2.组员
     */
    public Short getUserRole() {
        return userRole;
    }

    /**
     * 设置用户对于项目的角色：1.组长  2.组员
     *
     * @param userRole 用户对于项目的角色：1.组长  2.组员
     */
    public void setUserRole(Short userRole) {
        this.userRole = userRole;
    }

    /**
     * @return user_nickname
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * @param userNickname
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    /**
     * @return user_realname
     */
    public String getUserRealname() {
        return userRealname;
    }

    /**
     * @param userRealname
     */
    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return delete_time
     */
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * @param deleteTime
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}