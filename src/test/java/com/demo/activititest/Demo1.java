package com.demo.activititest;

import com.demo.DemoApplication;
import com.demo.SecurityUtil;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstanceMeta;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.model.builders.StartProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.builders.CompleteTaskPayloadBuilder;
import org.activiti.api.task.model.builders.CreateTaskVariablePayloadBuilder;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.model.payloads.CompleteTaskPayload;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class Demo1 {

    private static final Logger logger = LoggerFactory.getLogger(Demo1.class);
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

    @Test
    public void deploy() {
        ProcessEngines.getDefaultProcessEngine();

        logger.info("发布流程");
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("leave.bpmn")
                .name("请假审批2")
                .deploy();

        logger.info("启动流程");
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("leave_bpmn1");
        logger.info("流程实例id：" + instance.getId());
        logger.info("流程定义id：" + instance.getProcessDefinitionId());

        logger.info("完成任务");
        Map<String, Object> variables = new HashMap<>();
        variables.put("day", 4);
        taskService.complete(taskService.createTaskQuery().list().get(0).getId(), variables);
    }

    @Test
    public void startProcess() {
//        Map<String, Object> variables = new HashMap<>();
//        variables.put("day", 4);
        ProcessInstance instance = runtimeService.startProcessInstanceByKey("leave_bpmn1");
        logger.info("流程实例id：" + instance.getId());
        logger.info("流程定义id：" + instance.getProcessDefinitionId());

    }

    @Test
    public void taskQuery() {
        List<Task> list = taskService.createTaskQuery()
                .list();
        for (Task task : list) {
            logger.info("任务id：" + task.getId());
            logger.info("任务名称:" + task.getName());
            logger.info("任务的创建时间:" + task.getCreateTime());
            logger.info("任务的办理人:" + task.getAssignee());
            logger.info("流程实例ID：" + task.getProcessInstanceId());
            logger.info("执行对象ID:" + task.getExecutionId());
            logger.info("流程定义ID:" + task.getProcessDefinitionId());
        }
    }

    @Test
    public void complete() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("day", 2);
        taskService.complete(taskService.createTaskQuery().list().get(0).getId(), variables);
    }

    @Test
    public void newservice_deploy() {
        securityUtil.logInAs("se_user1");
        Page<ProcessDefinition> definitions = processRuntime.processDefinitions(Pageable.of(0, 10));
        for (ProcessDefinition definition : definitions.getContent()) {
            logger.info("processdefinition:" + definition);
        }
    }

    @Test
    public void newservice_start() {
        securityUtil.logInAs("se_user1");
        org.activiti.api.process.model.ProcessInstance instance = processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionId("leave_bpmn1:2:aa5ab5ea-ca40-11e9-8970-1002b52d6afe")
                .withProcessDefinitionKey("leave_bpmn1")
                .build());
        logger.info(instance.getId());//97c5a7df-ca48-11e9-9444-1002b52d6afe
    }

    @Test
    public void instanceList() {
        securityUtil.logInAs("se_user1");
        List<org.activiti.api.process.model.ProcessInstance> instances = processRuntime.processInstances(Pageable.of(0, 10)).getContent();
        for (org.activiti.api.process.model.ProcessInstance instance : instances) {
            logger.info("流程实例：" + instance);
            ProcessInstanceMeta instantceMeta = processRuntime.processInstanceMeta(instance.getId());
            logger.info("流程实例其他信息：" + instantceMeta.getActiveActivitiesIds());
        }
    }

    @Test
    public void taskList() {
        securityUtil.logInAs("se_user1");
        Page<org.activiti.api.task.model.Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));
        logger.info("tasks:" + tasks.getContent());
        for (org.activiti.api.task.model.Task task : tasks.getContent()) {
            logger.info("任务：" + task);
        }
    }

    @Test
    public void completeTask() {
        securityUtil.logInAs("se_user1");
        Map<String, Object> variables = new HashMap<>();
        variables.put("day", 2);
        taskRuntime.complete(new CompleteTaskPayloadBuilder()

                .withTaskId("97cde544-ca48-11e9-9444-1002b52d6afe")
                .withVariables(variables)
                .build());

    }


    @Test
    public void test2() {
        securityUtil.logInAs("se_user1");
        String taskid = "07d15e43-cadf-11e9-b027-1002b52d6afe";
//        taskRuntime.task(taskid);
        Map<String, Object> variables = new HashMap<>();
        variables.put("day", 2);
        taskRuntime.complete(new CompleteTaskPayloadBuilder()
                .withTaskId(taskid)
                .withVariables(variables)
                .build());
    }

    @Test
    public void test1() {
        securityUtil.logInAs("se_user1");

        ProcessEngines.getDefaultProcessEngine();

        logger.info("发布流程");
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("leave.bpmn")
                .name("请假审批2")
                .deploy();

        logger.info("启动流程");
        org.activiti.api.process.model.ProcessInstance instance = processRuntime.start(new StartProcessPayloadBuilder()
                .withProcessDefinitionKey("leave_bpmn1")
                .build());
        logger.info("流程实例id：" + instance.getId());
        logger.info("流程定义id：" + instance.getProcessDefinitionId());


//        logger.info("完成任务");
//        taskRuntime.task("");
    }
}
