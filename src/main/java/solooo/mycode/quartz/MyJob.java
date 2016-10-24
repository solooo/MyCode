package solooo.mycode.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:10/19-0019
 * History:
 * his1:
 */
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobParameter jobParam = (JobParameter) jobExecutionContext.getJobDetail().getJobDataMap()
                .get(JobParameter.JOB_PARAM);
        if (jobParam != null) {
            System.out.println(jobParam.getDescription());
        } else {
            System.out.println("Hey, can't find job parameter ...:)");
        }
    }
}
