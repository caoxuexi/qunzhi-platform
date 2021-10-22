package com.xidian.qunzhi.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author Cao Study
 * @description <h1>ChangePasswordDTO</h1>
 * @date 2021-10-22 21:35
 */
@ApiModel(value = "管理员分页搜索参数DTO",description = "管理员分页搜索项目的参数封装")
@Data
public class ChangePasswordDTO {
    @ApiModelProperty(value = "旧密码",name = "oldPassword",example = "123456",required = true)
    @Length(min = 5,max=16,message = "密码长度不对")
    private String oldPassword;

    @ApiModelProperty(value = "新密码",name = "newPassword",example = "1234567",required = true)
    @Length(min = 5,max=16,message = "密码长度不对")
    private String newPassword;

    @ApiModelProperty(value = "确认新密码",name = "reNewPassword",example = "1234567",required = true)
    @Length(min = 5,max=16,message = "密码长度不对")
    private String reNewPassword;
}
