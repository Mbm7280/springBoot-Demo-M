package com.ex.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 定时器配置类
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = {"com.ex.job"})
public class TaskConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }

    @Bean
    public Executor taskExecutor(){
        return new ScheduledThreadPoolExecutor(20,new BasicThreadFactory.Builder().namingPattern("Job-Thread-%d").build());
    }

}
