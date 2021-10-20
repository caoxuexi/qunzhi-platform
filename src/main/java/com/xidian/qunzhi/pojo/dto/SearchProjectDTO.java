package com.xidian.qunzhi.pojo.dto;

import com.xidian.qunzhi.pojo.basic.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Cao Study
 * @description <h1>SearchProjectDTO 管理员按条件查询project</h1>
 * @date 2021-10-20 20:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SearchProjectDTO extends PageDTO {
    private String name;
}
