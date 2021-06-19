package com.example.springcachespringboot.service;

import com.example.springcachespringboot.bean.User;
import com.example.springcachespringboot.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "scsb_user")
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUserList(){
        return userMapper.getUserList();
    }

    @Cacheable(cacheNames = "scsb_user", key = "#id")
    public User getUserById(Integer id){
        return userMapper.getUserById(id);
    }


}
