package com.xidian.qunzhi.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date firstOnline;

    private Integer cumulativeOnline;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date lastOnline;

    /**
     * 0:离线 1:在线
     */
    private Short state;


}
