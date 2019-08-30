package com.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HistoryTaskinstMapper {

    @Select("select * from act_hi_taskinst hi where hi.PROC_INST_ID_ = #{instance_id} order by hi.START_TIME_ desc")
    List<HistoryTaskinstEntity> selectListByInstanceId(String instance_id);
}
