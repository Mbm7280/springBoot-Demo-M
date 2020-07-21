package com.ex.job;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时器
 */
@Component
@Slf4j
public class TaskJob {

    /**
     * 按照标准时间来算
     * 每 10 秒执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void job1(){
        log.info("[Job1] 开始执行:{}", DateUtil.formatDateTime(new Date()));
    }

    /**
     * 从启动时间开始
     * 间隔两秒执行一次
     */
    @Scheduled(fixedDelay = 2000)
    public void job2(){
        log.info("[job2] 开始执行:{}",DateUtil.formatDateTime(new Date()));
    }

    /**
     * 从启动时间开始
     * 延迟5秒后，而后每四秒执行一次
     */
    @Scheduled(fixedDelay = 5000,initialDelay = 4000)
    public void jobe(){
        log.info("[job3] 开始执行:{}",DateUtil.formatDateTime(new Date()));
    }


}
