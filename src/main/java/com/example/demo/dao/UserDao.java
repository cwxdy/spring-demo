package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @Author: ChangYu
 * @Version 1.0
 */
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
