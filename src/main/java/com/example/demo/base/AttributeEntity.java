package com.example.demo.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AttributeEntity implements Serializable {

    private String attr1;
    private String attr1Name;
    private String attr2;
    private String attr2Name;
    private String attr3;
    private String attr3Name;
    private String attr4;
    private String attr4Name;
    private String attr5;
    private String attr5Name;
    private String attr6;
    private String attr6Name;
    private String attr7;
    private String attr7Name;
    private String attr8;
    private String attr8Name;
    private String attr9;
    private String attr9Name;
    private String attr10;
    private String attr10Name;
    @CreatedBy
    private String createdName;
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    private Date createdDate;
    @LastModifiedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    private Date lastModifyDate;

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

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

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr1Name() {
        return attr1Name;
    }

    public void setAttr1Name(String attr1Name) {
        this.attr1Name = attr1Name;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr2Name() {
        return attr2Name;
    }

    public void setAttr2Name(String attr2Name) {
        this.attr2Name = attr2Name;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getAttr3Name() {
        return attr3Name;
    }

    public void setAttr3Name(String attr3Name) {
        this.attr3Name = attr3Name;
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    public String getAttr4Name() {
        return attr4Name;
    }

    public void setAttr4Name(String attr4Name) {
        this.attr4Name = attr4Name;
    }

    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    public String getAttr5Name() {
        return attr5Name;
    }

    public void setAttr5Name(String attr5Name) {
        this.attr5Name = attr5Name;
    }

    public String getAttr6() {
        return attr6;
    }

    public void setAttr6(String attr6) {
        this.attr6 = attr6;
    }

    public String getAttr6Name() {
        return attr6Name;
    }

    public void setAttr6Name(String attr6Name) {
        this.attr6Name = attr6Name;
    }

    public String getAttr7() {
        return attr7;
    }

    public void setAttr7(String attr7) {
        this.attr7 = attr7;
    }

    public String getAttr7Name() {
        return attr7Name;
    }

    public void setAttr7Name(String attr7Name) {
        this.attr7Name = attr7Name;
    }

    public String getAttr8() {
        return attr8;
    }

    public void setAttr8(String attr8) {
        this.attr8 = attr8;
    }

    public String getAttr8Name() {
        return attr8Name;
    }

    public void setAttr8Name(String attr8Name) {
        this.attr8Name = attr8Name;
    }

    public String getAttr9() {
        return attr9;
    }

    public void setAttr9(String attr9) {
        this.attr9 = attr9;
    }

    public String getAttr9Name() {
        return attr9Name;
    }

    public void setAttr9Name(String attr9Name) {
        this.attr9Name = attr9Name;
    }

    public String getAttr10() {
        return attr10;
    }

    public void setAttr10(String attr10) {
        this.attr10 = attr10;
    }

    public String getAttr10Name() {
        return attr10Name;
    }

    public void setAttr10Name(String attr10Name) {
        this.attr10Name = attr10Name;
    }
}
