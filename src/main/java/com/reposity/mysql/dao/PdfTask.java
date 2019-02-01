package com.reposity.mysql.dao;


import javax.persistence.*;

@Entity
@Table(name = "pdf_task")
public class PdfTask {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "task_id")
    private String taskId;
    private Integer status;
    private Integer dpi;
    private String target;
    private String dest;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "state")
    private Integer state;
    @Column(name = "update_time")
    private String updateTime;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDpi() {
        return dpi;
    }

    public void setDpi(Integer dpi) {
        this.dpi = dpi;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
