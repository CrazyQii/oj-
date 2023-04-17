package com.oj.judge.runner;

import com.oj.judge.consumer.JudgeConsumer;
import javax.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class JudgeRunner implements ApplicationRunner {

    @Resource
    private JudgeConsumer judgeConsumer;

    @Override
    public void run(ApplicationArguments args){
        judgeConsumer.judge();
    }
}
