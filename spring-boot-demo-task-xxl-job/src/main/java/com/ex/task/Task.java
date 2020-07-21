package com.ex.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 *
 */
@Slf4j
@Component
@JobHandler("task")
public class Task extends IJobHandler {


    @Override
    public ReturnT<String> execute(String s) throws Exception {
        log.info("[s] = {}",s);
        XxlJobLogger.log("task demo run at :{}", DateUtil.now());
        return RandomUtil.randomInt(1,11) % 2 == 0 ? SUCCESS:FAIL;
    }
}
