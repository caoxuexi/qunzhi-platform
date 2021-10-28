package com.xidian.qunzhi.pojo;

import javax.persistence.*;

@Table(name = "project_extra")
public class ProjectExtra {
    @Id
    private Integer id;

    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "current_user_count")
    private Integer currentUserCount;

    private String demand;

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
     * @return current_user_count
     */
    public Integer getCurrentUserCount() {
        return currentUserCount;
    }

    /**
     * @param currentUserCount
     */
    public void setCurrentUserCount(Integer currentUserCount) {
        this.currentUserCount = currentUserCount;
    }

    /**
     * @return demand
     */
    public String getDemand() {
        return demand;
    }

    /**
     * @param demand
     */
    public void setDemand(String demand) {
        this.demand = demand;
    }
}