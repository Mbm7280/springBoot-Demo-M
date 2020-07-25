package com.ex;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppRun {

    public static void main(String[] args) {

        SpringApplication.run(AppRun.class,args);

    }

//    @Bean
//    public Snowflake snowflake() {
//        return IdUtil.createSnowflake(1, 1);
//    }

}
