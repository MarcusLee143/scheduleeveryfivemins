import java.lang.reflect.Method;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void scheduleTasks(Object object) {
        // Get all methods in the class
        Method[] methods = object.getClass().getDeclaredMethods();

        for (Method method : methods) {
            // Check if the method has the annotation
            if (method.isAnnotationPresent(ScheduleEveryFiveMins.class)) {
                // Schedule the method to run every 5 minutes
                scheduler.scheduleAtFixedRate(() -> {
                    try {
                        method.invoke(object); // Invoke the method
                    } catch (Exception e) {
                        e.printStackTrace(); // Handle any exceptions
                    }
                }, 0, 5, TimeUnit.MINUTES);
            }
        }
    }

    public void shutdown() {
        scheduler.shutdown(); // Shut down the scheduler when done
    }
}