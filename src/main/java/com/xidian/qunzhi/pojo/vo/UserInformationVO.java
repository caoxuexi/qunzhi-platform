package com.xidian.qunzhi.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author Cao Study
 * @description <h1>UserInformationVO</h1>
 * @date 2021-10-23 16:41
 */
@Data
public class UserInformationVO {
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
