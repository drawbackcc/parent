package com.example.ssmredis.schedule;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling //作用和<task:annotation-driven/>一样
public class TaskA {

    @Scheduled(cron = "0/60 * * * * ?")//每60秒执行一次
    public void hello(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ",A says hello to you~" + Thread.currentThread().getName());
    }

    @Scheduled(cron = "0/60 * * * * ?")//每60秒执行一次
    public void hi(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ",A says hi to you~" + Thread.currentThread().getName());
    }

}
