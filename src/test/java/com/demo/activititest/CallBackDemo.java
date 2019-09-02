package com.demo.activititest;

import com.demo.DemoApplication;
import com.demo.SecurityUtil;
import com.demo.mapper.HistoryActinstEntity;
import com.demo.service.HistoryActinstService;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CallBackDemo
{

    private static final Logger logger = LoggerFactory.getLogger(CallBackDemo.class);
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private TaskRuntime taskRuntime;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private HistoryService historyService;

    @Test
    public void test1() {
        logger.info("发布流程");
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("shen_pi.bpmn")
                .name("城管")
                .deploy();

        logger.info("启动流程");
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("shen_pi");
        logger.info("流程实例id：" + instance.getId());
        logger.info("流程定义id：" + instance.getProcessDefinitionId());

        //根据流程实例跟流程定义查询到指定的任务
//        List<Task> list = taskService.createTaskQuery()
//                .processInstanceId(instance.getId())
//                .list();
//        for (Task task : list) {
//            taskService.delegateTask(task.getId(),"se_user1");
//        }

    }
    @Test
    public void test2(){

        securityUtil.logInAs("se_user1");
        String assignee = "se_user1";
        //查询个人的待办任务
        logger.info("查询"+assignee+"的待办任务");
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .orderByTaskCreateTime()
                .asc()
                .list();

        for (Task task : list) {
            logger.info("流程实例id："+task.getProcessInstanceId());
            logger.info("任务id："+task.getId());
            logger.info("流程定义id："+task.getProcessDefinitionId());
        }
    }

    @Test
    public void test3(){
        String taskid = "4701b3b0-caf6-11e9-9361-1002b52d6afe";
        taskService.complete(taskid);
    }

    @Test
    public void test4(){
        String instanceid = "cccf27a6-caff-11e9-a762-1002b52d6afe";
        List<HistoricActivityInstance> actList = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(instanceid)
                .list();

        for (HistoricActivityInstance instance : actList) {
            logger.info("hi活动实例："+instance);
        }
        List<HistoricTaskInstance> taskList = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(instanceid)
                .list();
        for (HistoricTaskInstance instance : taskList) {
            logger.info("hi任务实例："+instance.getProcessVariables());
        }
        List<Execution> executionList = runtimeService.createExecutionQuery()
                .processInstanceId(instanceid)
                .list();
        for (Execution instance : executionList) {
            logger.info("活动实例："+instance);
        }



    }

}
