package com.nsm.quartztest;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HelloWorldScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {


        //创建调度器Scheduler
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();

        JobDetail job = JobBuilder.newJob(HelloWordJob.class).withIdentity("job1", "group1").build();

        JobDetail job2 = JobBuilder.newJob(CronJob.class).withIdentity("job2", "group2").build();

        Calendar startTime = Calendar.getInstance();
        startTime.add(Calendar.SECOND, 5);

        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.SECOND,30);

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "triggerGroup1").startAt(startTime.getTime()).endAt(endTime.getTime())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();

        CronTrigger trigger2 = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "triggerGroup2").startAt(startTime.getTime()).endAt(endTime.getTime())
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ? ")).build();

        scheduler.scheduleJob(job, trigger);
        scheduler.scheduleJob(job2, trigger2);
        scheduler.start();


    }
}
