package com.nsm.quartztest;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 *当任务运行时间大于任务运行间隔时间时
 * 配置该注解程序会等任务执行完毕以后再去执行,否则会在达到运行间隔时再启用新的线程执行
 */
@DisallowConcurrentExecution
/**
 * 配置该注解 当job执行完execute方法后会保存JobDataMap数据
 * 每个job拥有一个独立的JobDataMap（即使job运行时间大于设定间隔时间 开启了新的线程执行
 * 也是用一个JobDataMap 所以使用该注解时尽量加上第一个）
 *
 */
@PersistJobDataAfterExecution
public class CronJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        try {
            JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
            int count = jobDataMap.getIntFromString("count");
            ++count;
            jobDataMap.putAsString("count", count);
            System.out.println(key.getName() +"\"\"第"+ count +"次");
        }catch (Exception e){
            jobExecutionContext.getJobDetail().getJobDataMap().put("count", "1");
            System.out.println(key.getName() +"第1次");
        }
        String printTime = new SimpleDateFormat("yy-MM-dd HH-mm-ss").format(new Date());
        System.out.println(printTime + "I'm cron "+ key.getName());
    }
}
