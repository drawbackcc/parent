package com.example.exercise.controller;

import com.example.exercise.beans.User;
import com.example.exercise.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> hello(){
        return userService.getUserList();
    }

    @RequestMapping("/userData")
    public Map<String, Object> userData(User user){
        System.out.println(user);
        HashMap<String, Object> map = new HashMap<>();
        List<User> users = userService.getUserList();
        map.put("total", users.size());
        map.put("rows", users);
        return map;
    }

    @RequestMapping("/{id}")
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
