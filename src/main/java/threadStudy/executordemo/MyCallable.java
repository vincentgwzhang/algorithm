package threadStudy.executordemo;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyCallable implements Callable<WorkResult> {

    private String name;

    public MyCallable(final String name) {
        this.name = name;
    }

    @Override
    public WorkResult call() throws Exception {
        WorkResult result = new WorkResult();
        TimeUnit.SECONDS.sleep(new Random().nextInt(4));
        result.setStatus(name);

        System.out.println(name + " Run finish...");
        return result;
    }
}
