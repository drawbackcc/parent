package com.example.ssmredis.schedule;

import com.example.ssmredis.dao.CronMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration//用于标记配置类，兼备Component的效果。
public class DynamicScheduleTask implements SchedulingConfigurer {

//    @Mapper
//    @Repository
//    public interface CronMapper{
//        @Cacheable(value="conCache",key="#root.targetClass + #root.methodName")
//        @Select("select cron from cron limit 1")
//        String getCron();
//    }

    @Autowired
    @SuppressWarnings("all")
    CronMapper cronMapper;

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
//        registrar.addTriggerTask(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "执行动态任务" + Thread.currentThread().getName());
//            }
//        }, new Trigger() {
//            @Override
//            public Date nextExecutionTime(TriggerContext triggerContext) {
//                return null;
//            }
//        });

        registrar.addTriggerTask(
                () -> System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "执行动态任务" + Thread.currentThread().getName()),
                triggerContext -> {
                    String cron = cronMapper.getCron();
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
