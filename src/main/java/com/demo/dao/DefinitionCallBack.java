package com.demo.dao;

import ch.qos.logback.core.util.FileUtil;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class DefinitionCallBack {

    private static final Logger logger = LoggerFactory.getLogger(DefinitionCallBack.class);
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    /**
     * 将节点之后的节点删除然后指向新的节点。
     *
     * @param actDefId       流程定义ID
     * @param nodeId         流程节点ID
     * @param aryDestination 需要跳转的节点
     * @return Map<String, Object> 返回节点和需要恢复节点的集合。
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> prepare(String actDefId, String nodeId, String[] aryDestination) {
        Map<String, Object> map = new HashMap<String, Object>();

        //修改流程定义
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(actDefId);

        ActivityImpl curAct = processDefinition.findActivity(nodeId);
        List<PvmTransition> outTrans = curAct.getOutgoingTransitions();
        try {
            List<PvmTransition> cloneOutTrans = (List<PvmTransition>) FileUtil.cloneObject(outTrans);
            map.put("outTrans", cloneOutTrans);
        } catch (Exception ex) {

        }

        /**
         * 解决通过选择自由跳转指向同步节点导致的流程终止的问题。
         * 在目标节点中删除指向自己的流转。
         */
        for (Iterator<PvmTransition> it = outTrans.iterator(); it.hasNext(); ) {
            PvmTransition transition = it.next();
            PvmActivity activity = transition.getDestination();
            List<PvmTransition> inTrans = activity.getIncomingTransitions();
            for (Iterator<PvmTransition> itIn = inTrans.iterator(); itIn.hasNext(); ) {
                PvmTransition inTransition = itIn.next();
                if (inTransition.getSource().getId().equals(curAct.getId())) {
                    itIn.remove();
                }
            }
        }


        curAct.getOutgoingTransitions().clear();

        if (aryDestination != null && aryDestination.length > 0) {
            for (String dest : aryDestination) {
                //创建一个连接
                ActivityImpl destAct = processDefinition.findActivity(dest);
                TransitionImpl transitionImpl = curAct.createOutgoingTransition();
                transitionImpl.setDestination(destAct);
            }
        }

        map.put("activity", curAct);


        return map;

    }

    /**
     * 将临时节点清除掉，加回原来的节点。
     *
     * @param map void
     */
    @SuppressWarnings("unchecked")
    private void restore(Map<String, Object> map) {
        ActivityImpl curAct = (ActivityImpl) map.get("activity");
        List<PvmTransition> outTrans = (List<PvmTransition>) map.get("outTrans");
        curAct.getOutgoingTransitions().clear();
        curAct.getOutgoingTransitions().addAll(outTrans);
    }

    /**
     * 通过指定目标节点，实现任务的跳转
     *
     * @param taskId      任务ID
     * @param destNodeIds 跳至的目标节点ID
     * @param vars        流程变量
     */
    public synchronized void completeTask(String taskId, String[] destNodeIds, Map<String, Object> vars) {
        TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(taskId).singleResult();

        String curNodeId = task.getTaskDefinitionKey();
        String actDefId = task.getProcessDefinitionId();

        Map<String, Object> activityMap = prepare(actDefId, curNodeId, destNodeIds);
        try {
            taskService.complete(taskId);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            //恢复
            restore(activityMap);
        }
    }


    /**
     * 任务往下跳转
     *
     * @param taskId
     * @param jsonData
     * @param vars
     * @throws Exception
     */
    public void doNext(ProcessNextCmd cmd) throws Exception {

        boolean isSetBackPath = false;
        try {
            TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(cmd.getTaskId()).singleResult();

            UserTaskConfig userTaskConfig = bpmNodeSetManager.getTaskConfig(task.getSolId(), task.getTaskDefinitionKey());

            //String processInstanceId = task.getProcessInstanceId();
            // 加上executionId，用来记录执行的路径
            cmd.setNodeId(task.getTaskDefinitionKey());
            // 加上线程变量
            ProcessHandleHelper.setProcessCmd(cmd);
            BpmInst bpmInst = bpmInstManager.getByActInstId(task.getProcessInstanceId());
            BpmFormInst bpmFormInst = bpmFormInstManager.get(bpmInst.getFormInstId());
            try {
                String newJson = JSONUtil.copyJsons(bpmFormInst.getJsonData(), cmd.getJsonData());
                bpmFormInst.setJsonData(newJson);
                bpmFormInstManager.saveOrUpdate(bpmFormInst);
            } catch (Exception ex) {
                logger.error(ex.getCause());
            }
            Map<String, Object> vars = handleTaskVars(task, cmd.getJsonData());
            // 加上外围传过来的变量
            if (cmd.getVars() != null) {
                vars.putAll(cmd.getVars());
            }
            // 若为回退，则处理回退的操作
            if (TaskOptionType.BACK.name().equals(cmd.getJumpType())) {
                BpmRuPath bpmRuPath = getBackNodeId(task.getProcessInstanceId(), task.getTaskDefinitionKey());
            // 没有找到回退的节点，提示用户
                if (bpmRuPath == null) {
                    ProcessHandleHelper.getProcessMessage().getErrorMsges().add("本环节不能回退！没有找到上一步的回退审批环节!");
                    return;
                } else {// 设置回退的节点
                    cmd.setDestNodeId(bpmRuPath.getNodeId());
                    ProcessHandleHelper.setBackPath(bpmRuPath);
                    isSetBackPath = true;
                }
            } else if (TaskOptionType.BACK_TO_STARTOR.name().equals(cmd.getJumpType())) {// 回退至发起人
                ActNodeDef afterNode = actRepService.getNodeAfterStart(task.getProcessDefinitionId());
                if (afterNode == null) {
                    ProcessHandleHelper.getProcessMessage().getErrorMsges().add("没有找到发起人所在的审批环节！");
                    return;
                } else {
                    cmd.setDestNodeId(afterNode.getNodeId());
                }
            } else {
                // 查找是否为原路返回的模式，即当前任务是否由回退处理的
                BpmRuPath ruPath = bpmRuPathManager.getFarestPath(task.getProcessInstanceId(), task.getTaskDefinitionKey());
                if (ruPath != null && "".equals(ruPath.getNextJumpType())) {
                    BpmRuPath toNodePath = bpmRuPathManager.get(ruPath.getParentId());
                    if (toNodePath != null) {
                        cmd.setDestNodeId(toNodePath.getNodeId());
                    }
                }
            }

            //加上前置处理
            if (StringUtils.isNotEmpty(userTaskConfig.getPreHandle())) {
                Object preBean = AppBeanUtil.getBean(userTaskConfig.getPreHandle());
                if (preBean instanceof TaskPreHandler) {
                    TaskPreHandler handler = (TaskPreHandler) preBean;
                    handler.taskPreHandle(cmd, task, bpmInst.getBusKey());
                }
            }
            // 以下为任务的跳转处理
            if (StringUtils.isNotEmpty(cmd.getDestNodeId())) {// 进行指定节点的跳转
                actTaskService.completeTask(cmd.getTaskId(), new String[]{cmd.getDestNodeId()}, vars);
            } else {// 正常跳转
                taskService.complete(cmd.getTaskId(), vars);
            }

            //加上后置处理
            if (StringUtils.isNotEmpty(userTaskConfig.getAfterHandle())) {
                Object preBean = AppBeanUtil.getBean(userTaskConfig.getAfterHandle());
                if (preBean instanceof TaskAfterHandler) {
                    TaskAfterHandler handler = (TaskAfterHandler) preBean;
                    handler.taskAfterHandle(cmd, task.getTaskDefinitionKey(), bpmInst.getBusKey());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getCause());
            throw e;
        } finally {
            ProcessHandleHelper.clearProcessCmd();
            if (isSetBackPath) {
                ProcessHandleHelper.clearBackPath();
            }
        }
    }
}
