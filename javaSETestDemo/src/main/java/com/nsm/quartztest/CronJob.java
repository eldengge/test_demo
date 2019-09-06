package com.nsm.quartztest;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CronJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String printTime = new SimpleDateFormat("yy-MM-dd HH-mm-ss").format(new Date());
        System.out.println(printTime + "I'm cron Job!");
        jobExecutionContext.getJobDetail().getJobDataMap().put("info", "ok");
    }
}
