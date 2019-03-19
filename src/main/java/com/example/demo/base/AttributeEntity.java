package com.example.demo.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: ChangYu
 * @Version 1.0
 */
@MappedSuperclass
public class AttributeEntity  extends AuditEntity implements Serializable {
    @Column(length = 50)
    private String attr1;
    @Column(length = 50)
    private String attr2;
    @Column(length = 50)
    private String attr3;
    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }



}
