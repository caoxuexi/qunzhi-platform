package com.lq.mybatisgeneratordemo.mbg.mapper;

import com.lq.mybatisgeneratordemo.mbg.model.SaberAdmin;
import com.lq.mybatisgeneratordemo.mbg.model.SaberAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaberAdminMapper {
    int countByExample(SaberAdminExample example);

    int deleteByExample(SaberAdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SaberAdmin record);

    int insertSelective(SaberAdmin record);

    List<SaberAdmin> selectByExample(SaberAdminExample example);

    SaberAdmin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SaberAdmin record, @Param("example") SaberAdminExample example);

    int updateByExample(@Param("record") SaberAdmin record, @Param("example") SaberAdminExample example);

    int updateByPrimaryKeySelective(SaberAdmin record);

    int updateByPrimaryKey(SaberAdmin record);
}