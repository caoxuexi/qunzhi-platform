package pojo.dto;

import com.xidian.qunzhi.validator.PasswordEqual;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@PasswordEqual
@Getter
@Setter
@ApiModel(value = "用户注册对象DTO",description = "用户注册时需要的字段封装")
public class UserRegistDTO {
    @Email
    @NotEmpty(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱",name = "email",example = "752245683@qq.com",required = true)
    private String email;

    @Length(min = 5,max=16,message = "密码长度不对")
    @ApiModelProperty(value = "密码",name = "password",example = "123456",required = true)
    private String password;

    @Size(min = 5,max=16,message = "重输密码长度不对")
    @ApiModelProperty(value = "二次确认密码",name = "rePassword",example = "123456",required = true)
    private String rePassword;
}