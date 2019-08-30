package com.demo.service;

import com.demo.mapper.HistoryTaskinstEntity;
import com.demo.mapper.HistoryTaskinstMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HistoryTaskinstService {

    @Resource
    private HistoryTaskinstMapper taskinstMapper;

    /**
     * 根据实例id查询历史任务记录
     * @param  instance_id 实例id
     * @return List<HistoryActinstEntity>
     */
    public List<HistoryTaskinstEntity> selectListByInstanceId(String instance_id){
       return taskinstMapper.selectListByInstanceId(instance_id);
    }
}
