package com.example.activitispringboot.dao;

import com.example.activitispringboot.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> getUserList();

    User getUserById(@Param("id") Integer id);

    int updateUser(@Param("user")User user);

    int deleteUser(@Param("id")Integer id);
}
