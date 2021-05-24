package org.example.robot.utils.TASK;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.robot.utils.RobotUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author jiyec
 * @Date 2021/5/11 19:44
 * @Version 1.0
 **/
@Slf4j
public class CommonJob implements Job {
    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("执行任务");
        log.info("上一次执行时间：{}", jobExecutionContext.getPreviousFireTime());

        if(jobExecutionContext.getPreviousFireTime() == null){
            // 第一次执行
            RobotUtil.start();
        }

        // 普通任务内容
        SimpleDateFormat minuteSDF = new SimpleDateFormat("mm");
        SimpleDateFormat hourSDF = new SimpleDateFormat("HH");
        Date date = new Date();
        int minute = Integer.parseInt(minuteSDF.format(date));
        int hour = Integer.parseInt(hourSDF.format(date));



    }
}
