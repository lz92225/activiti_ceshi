package com.demo.mapper;

import java.sql.Date;

public class HistoryActinstEntity {

    private String ID_;//id
    private String PROC_DEF_ID_;//定义id
    private String PROC_INST_ID_;//实例id
    private String EXECUTION_ID_;//活动id
    private String ACT_ID_;//活动id
    private String TASK_ID_;//任务id
    private String CALL_PROC_INS;//
    private String ACT_NAME_;//节点name
    private String ACT_TYPE_;//节点类型
    private String ASSIGNEE_;//执行人
    private Date START_TIME_;//开始时间
    private Date END_TIME_;//结束时间
    private long DURATION_;//耗时
    private String DELETE_REASON_;//删除原因
    private String TENANT_ID_;//

    public String getID_() {
        return ID_;
    }

    public void setID_(String ID_) {
        this.ID_ = ID_;
    }

    public String getPROC_DEF_ID_() {
        return PROC_DEF_ID_;
    }

    public void setPROC_DEF_ID_(String PROC_DEF_ID_) {
        this.PROC_DEF_ID_ = PROC_DEF_ID_;
    }

    public String getPROC_INST_ID_() {
        return PROC_INST_ID_;
    }

    public void setPROC_INST_ID_(String PROC_INST_ID_) {
        this.PROC_INST_ID_ = PROC_INST_ID_;
    }

    public String getEXECUTION_ID_() {
        return EXECUTION_ID_;
    }

    public void setEXECUTION_ID_(String EXECUTION_ID_) {
        this.EXECUTION_ID_ = EXECUTION_ID_;
    }

    public String getACT_ID_() {
        return ACT_ID_;
    }

    public void setACT_ID_(String ACT_ID_) {
        this.ACT_ID_ = ACT_ID_;
    }

    public String getTASK_ID_() {
        return TASK_ID_;
    }

    public void setTASK_ID_(String TASK_ID_) {
        this.TASK_ID_ = TASK_ID_;
    }

    public String getCALL_PROC_INS() {
        return CALL_PROC_INS;
    }

    public void setCALL_PROC_INS(String CALL_PROC_INS) {
        this.CALL_PROC_INS = CALL_PROC_INS;
    }

    public String getACT_NAME_() {
        return ACT_NAME_;
    }

    public void setACT_NAME_(String ACT_NAME_) {
        this.ACT_NAME_ = ACT_NAME_;
    }

    public String getACT_TYPE_() {
        return ACT_TYPE_;
    }

    public void setACT_TYPE_(String ACT_TYPE_) {
        this.ACT_TYPE_ = ACT_TYPE_;
    }

    public String getASSIGNEE_() {
        return ASSIGNEE_;
    }

    public void setASSIGNEE_(String ASSIGNEE_) {
        this.ASSIGNEE_ = ASSIGNEE_;
    }

    public Date getSTART_TIME_() {
        return START_TIME_;
    }

    public void setSTART_TIME_(Date START_TIME_) {
        this.START_TIME_ = START_TIME_;
    }

    public Date getEND_TIME_() {
        return END_TIME_;
    }

    public void setEND_TIME_(Date END_TIME_) {
        this.END_TIME_ = END_TIME_;
    }

    public long getDURATION_() {
        return DURATION_;
    }

    public void setDURATION_(long DURATION_) {
        this.DURATION_ = DURATION_;
    }

    public String getDELETE_REASON_() {
        return DELETE_REASON_;
    }

    public void setDELETE_REASON_(String DELETE_REASON_) {
        this.DELETE_REASON_ = DELETE_REASON_;
    }

    public String getTENANT_ID_() {
        return TENANT_ID_;
    }

    public void setTENANT_ID_(String TENANT_ID_) {
        this.TENANT_ID_ = TENANT_ID_;
    }
}
