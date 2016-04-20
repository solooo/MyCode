package solooo.mycode.file;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/4/19.
 */
public class FileScheduled implements Runnable {

    private String jobName;

    private int count = 0;

    ScheduledExecutorService service;

    public FileScheduled() {

    }

    public FileScheduled(String name) {
        super();
        this.jobName = name;
    }

    @Override
    public void run() {
        if(count == 5) {
            service.shutdown();
            setJob("小刚", 5);
        }
        System.out.println(jobName + " say hello world!");
        count++;
    }

    public void setJob(String name, int period) {
        service = Executors
                .newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new FileScheduled(name), 0, period, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        new FileScheduled().setJob("小明", 1);
    }

}
