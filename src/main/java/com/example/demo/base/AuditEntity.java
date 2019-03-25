package com.example.demo.base;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: ChangYu
 * @Date: 3/19/2019 11:29 AM
 * @Version 1.0
 */
public class AuditEntity   implements Serializable {
    private Date createdDate;
    private Date lastModifyDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
}
