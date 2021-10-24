package com.xidian.qunzhi.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "device_log")
public class DeviceLog {
    @Id
    private String id;

    @Column(name = "project_id")
    private Integer projectId;

    private String mac;

    @Column(name = "first_online")
    private Date firstOnline;

    @Column(name = "cumulative_online")
    private Integer cumulativeOnline;

    @Column(name = "last_online")
    private Date lastOnline;

    /**
     * 0:离线 1:在线
     */
    private Short state;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
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
     * @return mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * @param mac
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * @return first_online
     */
    public Date getFirstOnline() {
        return firstOnline;
    }

    /**
     * @param firstOnline
     */
    public void setFirstOnline(Date firstOnline) {
        this.firstOnline = firstOnline;
    }

    /**
     * @return cumulative_online
     */
    public Integer getCumulativeOnline() {
        return cumulativeOnline;
    }

    /**
     * @param cumulativeOnline
     */
    public void setCumulativeOnline(Integer cumulativeOnline) {
        this.cumulativeOnline = cumulativeOnline;
    }

    /**
     * @return last_online
     */
    public Date getLastOnline() {
        return lastOnline;
    }

    /**
     * @param lastOnline
     */
    public void setLastOnline(Date lastOnline) {
        this.lastOnline = lastOnline;
    }

    /**
     * 获取0:离线 1:在线
     *
     * @return state - 0:离线 1:在线
     */
    public Short getState() {
        return state;
    }

    /**
     * 设置0:离线 1:在线
     *
     * @param state 0:离线 1:在线
     */
    public void setState(Short state) {
        this.state = state;
    }
}