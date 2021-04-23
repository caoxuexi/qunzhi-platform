package com.lq.mybatisgeneratordemo.dto;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class SaberUserParam implements Serializable {

    @ApiModelProperty(value = "用户名称（邮箱格式）")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "性别：0 未知， 1男， 1 女")
    private Byte gender;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "用户头像图片")
    private String avatar;

    @ApiModelProperty(value = "用户真实姓名")
    private String realname;

    @ApiModelProperty(value = "用户手机号码")
    private String mobile;

    @ApiModelProperty(value = "用户QQ账号")
    private String qqNumber;

    @ApiModelProperty(value = "用户所在省份")
    private String province;

    @ApiModelProperty(value = "用户所在市区")
    private String city;

    @ApiModelProperty(value = "用户所在区县")
    private String country;

    @ApiModelProperty(value = "用户详细地址")
    private String addressDetail;

    @ApiModelProperty(value = "创建时间")
    private Date addTime;

    private static final long serialVersionUID = 1L;
}
