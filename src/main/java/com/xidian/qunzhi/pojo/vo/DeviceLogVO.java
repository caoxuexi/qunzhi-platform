package com.xidian.qunzhi.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author beiqixing
 * @Description
 * @Date 2021/10/25 13:23
 */
@Data
public class DeviceLogVO {
    private String mac;

    private Integer projectId;

    private Date firstOnline;

    private Integer cumulativeOnline;

    private Date lastOnline;

    /**
     * 0:离线 1:在线
     */
    private Short state;


}
