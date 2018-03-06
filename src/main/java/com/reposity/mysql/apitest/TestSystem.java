package com.reposity.mysql.apitest;

import javax.persistence.*;

@Entity
@Table(name = "system")
public class TestSystem {

    @Id
    @GeneratedValue
    private Long id;
    private String sysname;
    private String description;
    private String status;
    @Column(name="create_time")
    private String createtime;
    @Column(name="update_time")
    private String updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysname() {
        return sysname;
    }

    public void setSysname(String sysname) {
        this.sysname = sysname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String create_time) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String update_time) {
        this.updatetime = updatetime;
    }
}
