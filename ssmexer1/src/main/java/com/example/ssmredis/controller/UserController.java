package com.example.ssmredis.controller;

import com.example.ssmredis.bean.User;
import com.example.ssmredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> hello(){
        return userService.getUserList();
    }

    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable("id")Integer id){
        return userService.getUserById(id);
    }

    @RequestMapping("/request")
    public Map<String, Object> request(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> map = new HashMap<>();
//        request.getRequestDispatcher("").forward(request, response);
//        response.sendRedirect("");
        map.put("request", request);
        return map;
    }


}
