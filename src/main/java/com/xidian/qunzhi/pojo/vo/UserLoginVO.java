package com.xidian.qunzhi.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Cao Study
 * @description <h1>UserVO</h1>
 * @date 2021-10-16 19:28
 */
@ApiModel(value = "用户登录成功时返回的VO",description = "用户登录成功返回给前端的对象")
@Data
public class UserLoginVO {
    @ApiModelProperty(value = "id",name = "id",example = "1",required = true)
    private Integer id;
    @ApiModelProperty(value = "邮箱",name = "email",example = "752245683@qq.com",required = true)
    private String email;
    @ApiModelProperty(value = "昵称",name = "nickname",example = "caoxuexi",required = true)
    private String nickname;
    @ApiModelProperty(value = "令牌",name = "token",example = "asdadas.axxsd.asda",required = true)
    private String token;
    @ApiModelProperty(value = "是否是管理员",name = "isAdmin",example = "1",required = true)
    private String isAdmin;
}
