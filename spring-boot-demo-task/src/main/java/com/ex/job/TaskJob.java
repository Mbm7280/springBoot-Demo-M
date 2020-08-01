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
     * 参数详解                 可匹配字符                           允许值
     * 第一个参数:   秒           ,   -   *   /                       0-59
     * 第二个参数:   分           ,   -   *   /                       0-59
     * 第三个参数:   时           ,   -   *   /                       0-23
     * 第四个参数:   天           ,   -   *   /   ?   L   W           1-31
     * 第五个参数:   月           ,   -   *   /                       1-12
     * 第六个参数:   星期         ,    -   *   /   ?   L   W   #       1-7
     * 第七个参数:   年           ,   -   *   /   一般用不上           1970-2099
     *
     */
    /**
     * 秒执行
     */
    @Scheduled(cron = "0/2 * * * * *")
    public void jobb1(){log.info("[Job_1]-Begin:" + DateUtil.formatDateTime(new Date()));}

    /**
     * 分钟执行
     */
    @Scheduled(cron = "0 0/1 * * * *")
    public void jobb2(){log.info("[Job_2-Begin]:" + DateUtil.formatDateTime(new Date()));}

    /**
     * 小时执行
     */
    @Scheduled(cron = "0 0 0/1 * * *")
    public void jobb3(){log.info("[Job_3-Begin]:" + DateUtil.formatDateTime(new Date()));}

    /**
     * 天执行
     */
    @Scheduled(cron = "0 0 0 1 * *")
    public void jobb4(){log.info("[Job_4-Begin]:" + DateUtil.formatDateTime(new Date()));}

    /**
     * 每月一号执行
     */
    @Scheduled(cron = "0 0 0 0 1/1 ?")
    public void jobb5(){log.info("[jobb5-Begin]:" + DateUtil.formatDateTime(new Date()) );}

    /**
     * 每周一执行
     */
    @Scheduled(cron = "0 0 0 0 * 2 ")
    public void jobb6(){log.info("[jobb6-Begin]: " + DateUtil.formatDateTime(new Date()));}

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
