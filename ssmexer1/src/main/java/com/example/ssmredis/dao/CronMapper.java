package com.example.ssmredis.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public interface CronMapper {

    @Cacheable(value="conCache",key="#root.targetClass + #root.methodName")
    @Select("select cron from cron limit 1")
    String getCron();
}
