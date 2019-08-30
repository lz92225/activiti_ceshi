package com.demo.service;

import com.demo.mapper.HistoryActinstEntity;
import com.demo.mapper.HistoryActinstMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HistoryActinstService {

    @Resource
    private HistoryActinstMapper actinstMapper;

    /**
     * 根据实例id查询历史活动记录
     * @param  instance_id 实例id
     * @return List<HistoryActinstEntity>
     */
    public List<HistoryActinstEntity> selectListByInstanceId(String instance_id){
        return actinstMapper.selectListByInstanceId(instance_id);
    }

}
