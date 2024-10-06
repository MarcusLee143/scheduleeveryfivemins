import java.util.Date;

public class MyScheduledJobs {

    @ScheduleEveryFiveMins
    public void myJob() {
        System.out.println("The current time is: " + new Date());
    }

    public static void main(String[] args) {
        MyScheduledJobs jobs = new MyScheduledJobs();
        Scheduler scheduler = new Scheduler();

        // Start scheduling jobs
        scheduler.scheduleTasks(jobs);

        // Add a shutdown hook to gracefully shut down the scheduler
        Runtime.getRuntime().addShutdownHook(new Thread(scheduler::shutdown));
    }
}