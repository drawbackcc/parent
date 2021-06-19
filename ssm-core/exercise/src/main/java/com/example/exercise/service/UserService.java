package com.example.exercise.service;

import com.example.exercise.beans.User;
import com.example.exercise.dao.UserMapper;
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
