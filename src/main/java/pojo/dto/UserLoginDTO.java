package pojo.dto;

import com.xidian.qunzhi.validator.PasswordEqual;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@ApiModel(value = "用户登录对象DTO",description = "用户登录时需要的字段封装")
public class UserLoginDTO {
    @Email
    @NotEmpty(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱",name = "email",example = "752245683@qq.com",required = true)
    private String email;

    @Size(min = 5,max=16,message = "密码长度不对")
    @ApiModelProperty(value = "密码",name = "password",example = "123456",required = true)
    private String password;

}