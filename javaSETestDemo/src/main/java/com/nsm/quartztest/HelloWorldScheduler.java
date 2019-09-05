package com.nsm.quartztest;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

public class HelloWorldScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {

        //创建调度器Scheduler
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();

        JobDetail job = JobBuilder.newJob(HelloWordJob.class).withIdentity("job1", "group1").build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();

        scheduler.scheduleJob(job, trigger);
        System.out.println("start");
        scheduler.start();

        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("end");

    }
}
