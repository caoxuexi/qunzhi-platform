package com.xidian.qunzhi.pojo.dto;

import com.xidian.qunzhi.pojo.basic.PageDTO;
import com.xidian.qunzhi.pojo.basic.PageVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Cao Study
 * @description <h1>SearchUserDTO</h1>
 * @date 2021-10-25 22:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "管理员分页搜索用户参数DTO",description = "管理员分页搜索项目的参数封装")
public class SearchUserDTO extends PageDTO {
    @ApiModelProperty(value = "用户昵称",name = "nickname",example = "caoxuexi",required = false)
    private String nickname;

    @ApiModelProperty(value = "用户真名",name = "realname",example = "曹操",required = false)
    private String realname;

    @ApiModelProperty(value = "邮箱",name = "email",example = "752245683@qq.com",required = false)
    private String email;
}
