package com.example.springcachespringboot.controller;

import com.example.springcachespringboot.bean.User;
import com.example.springcachespringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String hello(Model model){
        System.out.println("index controller------");
        model.addAttribute("list", userService.getUserList());
        return "index";
    }

    @ResponseBody
    @RequestMapping("user/{id}")
    public User getUser(@PathVariable("id")Integer id){
        return userService.getUserById(id);
    }
}
