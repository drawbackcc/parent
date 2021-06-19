package com.example.ssmredis.controller;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.concurrent.Callable;

@EnableAsync
@Controller
public class OtherController {

    public String upload(HttpServletRequest request){
        return "index";
    }

    @GetMapping("callable")
    @ResponseBody
    public Callable<String> callable() {
        System.out.println(LocalDateTime.now().toString() + "--->主线程开始" + Thread.currentThread().getName());
        Callable<String> callable = () -> {
            String result = "congratulation!!!";
            // 执行业务耗时 5s
            Thread.sleep(5000);
            System.out.println(LocalDateTime.now().toString() + "--->子任务线程("+Thread.currentThread().getName()+")");
            return result;
        };
        System.out.println(LocalDateTime.now().toString() + "--->主线程结束" + Thread.currentThread().getName());

        return callable;
    }
}
