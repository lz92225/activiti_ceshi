package com.demo.mapper;

import java.sql.Date;

public class HistoryTaskinstEntity {

    private String ID_;//id
    private String PROC_DEF_ID_;//定义id
    private String TASK_DEF_KEY_;//
    private String PROC_INST_ID_;//实例id
    private String EXECUTION_ID_;//活动id
    private String NAME_;//
    private String PARENT_TASK_ID_;//父任务id
    private String DESCRIPTION_;//描述信息
    private String OWNER_;//发起人
    private String ASSIGNEE_;//执行人
    private Date START_TIME_;//
    private Date CLAIM_TIME_;//声明时间
    private Date END_TIME_;//
    private long DURATION_;//耗时
    private String DELETE_REASON_;//
    private int PRIORITY_;//优先级  默认50
    private Date DUE_DATE_;//到期时间
    private String FORM_KEY_;//
    private String CATEGORY_;//
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

    public String getTASK_DEF_KEY_() {
        return TASK_DEF_KEY_;
    }

    public void setTASK_DEF_KEY_(String TASK_DEF_KEY_) {
        this.TASK_DEF_KEY_ = TASK_DEF_KEY_;
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

    public String getNAME_() {
        return NAME_;
    }

    public void setNAME_(String NAME_) {
        this.NAME_ = NAME_;
    }

    public String getPARENT_TASK_ID_() {
        return PARENT_TASK_ID_;
    }

    public void setPARENT_TASK_ID_(String PARENT_TASK_ID_) {
        this.PARENT_TASK_ID_ = PARENT_TASK_ID_;
    }

    public String getDESCRIPTION_() {
        return DESCRIPTION_;
    }

    public void setDESCRIPTION_(String DESCRIPTION_) {
        this.DESCRIPTION_ = DESCRIPTION_;
    }

    public String getOWNER_() {
        return OWNER_;
    }

    public void setOWNER_(String OWNER_) {
        this.OWNER_ = OWNER_;
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

    public Date getCLAIM_TIME_() {
        return CLAIM_TIME_;
    }

    public void setCLAIM_TIME_(Date CLAIM_TIME_) {
        this.CLAIM_TIME_ = CLAIM_TIME_;
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

    public int getPRIORITY_() {
        return PRIORITY_;
    }

    public void setPRIORITY_(int PRIORITY_) {
        this.PRIORITY_ = PRIORITY_;
    }

    public Date getDUE_DATE_() {
        return DUE_DATE_;
    }

    public void setDUE_DATE_(Date DUE_DATE_) {
        this.DUE_DATE_ = DUE_DATE_;
    }

    public String getFORM_KEY_() {
        return FORM_KEY_;
    }

    public void setFORM_KEY_(String FORM_KEY_) {
        this.FORM_KEY_ = FORM_KEY_;
    }

    public String getCATEGORY_() {
        return CATEGORY_;
    }

    public void setCATEGORY_(String CATEGORY_) {
        this.CATEGORY_ = CATEGORY_;
    }

    public String getTENANT_ID_() {
        return TENANT_ID_;
    }

    public void setTENANT_ID_(String TENANT_ID_) {
        this.TENANT_ID_ = TENANT_ID_;
    }
}
