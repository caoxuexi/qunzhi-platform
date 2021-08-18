package com.lq.mybatisgeneratordemo.mbg.mapper;

import com.lq.mybatisgeneratordemo.mbg.model.SaberUser;
import com.lq.mybatisgeneratordemo.mbg.model.SaberUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaberUserMapper {
    int countByExample(SaberUserExample example);

    int deleteByExample(SaberUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaberUser record);

    int insertSelective(SaberUser record);

    List<SaberUser> selectByExample(SaberUserExample example);

    SaberUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaberUser record, @Param("example") SaberUserExample example);

    int updateByExample(@Param("record") SaberUser record, @Param("example") SaberUserExample example);

    int updateByPrimaryKeySelective(SaberUser record);

    int updateByPrimaryKey(SaberUser record);
}