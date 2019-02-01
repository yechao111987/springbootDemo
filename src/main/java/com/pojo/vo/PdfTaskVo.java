package com.pojo.vo;

import javax.persistence.Column;
import java.util.List;

/**
 * @Author yechao111987@126.com
 * @date 2019/2/1 10:57
 */
public class PdfTaskVo {
    private String taskId;
    private String status;
    private Integer dpi;
    private String target;
    private List<String> dest;
    private String createTime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public List<String> getDest() {
        return dest;
    }

    public void setDest(List<String> dest) {
        this.dest = dest;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
