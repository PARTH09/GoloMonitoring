package com.golo.golomonitorapi.scheduler;

import com.golo.golomonitorapi.services.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class Scheduler implements SchedulingConfigurer {


    @Autowired
    private MonitoringService monitoringService;

    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(1);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar){

        scheduledTaskRegistrar.scheduleTriggerTask(new TriggerTask(
                () -> {
                    try {
                        monitoringService.apiScheduler();
                    }catch (Exception ex){
                        ex.getMessage();
                    }
                },
                triggerContext -> {
                    Calendar nextExecutionTime = new GregorianCalendar();
                    Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
                    nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
                    nextExecutionTime.add(Calendar.MILLISECOND, getNextExecutionTime());
                    return nextExecutionTime.getTime();
                })
        );
    }

    public int getNextExecutionTime() {
        return new Random().nextInt(5) + 1;
    }
}