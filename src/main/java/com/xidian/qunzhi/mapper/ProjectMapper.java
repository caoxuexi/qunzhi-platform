package com.xidian.qunzhi.mapper;


import com.xidian.qunzhi.pojo.Project;
import com.xidian.qunzhi.utils.MyMapper;

import java.util.List;

public interface ProjectMapper extends MyMapper<Project> {
    List<Project> listAllByUserId(Integer id);

    Integer getCount();
}