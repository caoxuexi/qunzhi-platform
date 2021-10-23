package com.xidian.qunzhi.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author Cao Study
 * @description <h1>UserInformationDTO</h1>
 * @date 2021-10-22 22:36
 */
@ApiModel(value = "用户登录对象DTO", description = "用户登录时需要的字段封装")
@Data
public class UserInformationDTO {

    @ApiModelProperty(value = "昵称", name = "nickname", example = "caoxuexi", required = true)
    @NotEmpty
    String nickname;

    @ApiModelProperty(value = "真实姓名", name = "realname", example = "曹操")
    @NotEmpty
    String realname;

    @ApiModelProperty(value = "手机号码", name = "mobile", example = "18874124879")
    @Pattern(regexp = "^(?:(?:\\+|00)86)?1(?:(?:3[\\d])|(?:4[5-79])|(?:5[0-35-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\\d])|(?:9[189]))\\d{8}$",
            message = "手机号码不符合规范")
    String mobile;

    @ApiModelProperty(value = "QQ号码", name = "qq", example = "11111")
    @Pattern(regexp = "^[1-9][0-9]{4,10}$",message = "QQ号不符合规范")
    String qq;

    @ApiModelProperty(value = "地址", name = "address", example = "浙江省杭州市萧山区萧山经济开发区钱农东路8号西电杭州研究院")
    String address;

    @ApiModelProperty(value = "地区", name = "district", example = "浙江省/杭州市/萧山区")
    String district;

}
