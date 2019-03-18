package com.example.demo.entity;
import com.example.demo.base.AttributeEntity;
import javax.persistence.*;
import java.io.Serializable;
/**
 * @Author: ChangYu
 * @Version 1.0
 */
@Entity
@Table(name = "SPRING_BOOT_USER")
public class User extends AttributeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
