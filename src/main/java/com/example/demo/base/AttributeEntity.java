package com.example.demo.base;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

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

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }



}
