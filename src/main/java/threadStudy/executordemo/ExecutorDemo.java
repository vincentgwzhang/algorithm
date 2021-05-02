package threadStudy.executordemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<WorkResult>> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable<WorkResult> worker = new MyCallable("Callable-S-" +i);
            Future<WorkResult> future = executorService.submit(worker);
            lists.add(future);
        }
        executorService.shutdown();

        lists.forEach(future -> {
            try {
                System.out.println("future result: " + future.get().getStatus());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

}
