package com.example.activitispringboot.service;

import com.example.activitispringboot.bean.User;
import com.example.activitispringboot.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUserList(){
        return userMapper.getUserList();
    }

    public User getUserById(Integer id){
        return userMapper.getUserById(id);
    }


}
