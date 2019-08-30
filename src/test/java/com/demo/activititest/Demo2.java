package com.demo.activititest;

import com.demo.exception.AppException;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Demo2 {

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private TaskRuntime taskRuntime;

    @Autowired
    private TaskService taskService;

    @Test
    public void backProcess(String taskId, String activityId,

                            Map<String, Object> variables) throws Exception {

        if (StringUtils.isEmpty(activityId)) {

            throw new AppException("-1", "退回目标节点ID为空！");

        }


        // 查找所有并行任务节点，同时驳回

//        List<Task> taskList = findTaskListByKey(findProcessInstanceByTaskId(
//
//                taskId).getId(), findTaskById(taskId).getTaskDefinitionKey());
//
//        for (Task task : taskList) {
//
//            commitProcess(task.getId(), variables, activityId);
//
//        }

    }

    private List<Task> findTaskListByKey(String processInstanceId, String key) {
        List<Task> list = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .taskDefinitionKey(key).list();
        return list;
    }
}
