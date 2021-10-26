package com.xidian.qunzhi.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Cao Study
 * @description <h1>UserAdminVO</h1>
 * @date 2021-10-25 22:33
 */
@Data
public class UserAdminVO {
    String email;

    String nickname;

    String realname;

    String mobile;

    String qq;

    String address;

    String district;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    Date createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    Date updateTime;
}
