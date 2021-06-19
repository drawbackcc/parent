package com.example.ssmredis.service;

import com.example.ssmredis.bean.User;
import com.example.ssmredis.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "ssmr_user")
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUserList(){
        return userMapper.getUserList();
    }

    @Cacheable(cacheNames = "ssmr_user", key = "#id")
    public User getUserById(Integer id){
        return userMapper.getUserById(id);
    }

}
