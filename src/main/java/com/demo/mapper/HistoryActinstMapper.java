package com.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HistoryActinstMapper {


    @Select("select * from act_hi_actinst hi where hi.PROC_INST_ID_ = #{instance_id} order by hi.START_TIME_ desc")
    List<HistoryActinstEntity> selectListByInstanceId(String instance_id);

}
