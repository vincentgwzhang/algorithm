package interview.cachestudy.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class GuavaCacheService2 {


    private static Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(3).build();

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(() -> {
            try {
                String value = cache.get("key", () -> {
                    Thread.sleep(2000); //模拟加载时间
                    return "[Value]:thread1";
                });
                countDownLatch.countDown();
                System.out.println("thread1 >> " + value); // 该答案可能来源于下面的分线程
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                String value = cache.get("key", () -> {
                    Thread.sleep(1000); //模拟加载时间
                    return "[Value]:thread2";
                });
                countDownLatch.countDown();
                System.out.println("thread2 >> " + value); // 该答案可能来源于上面的分线程
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        countDownLatch.await();
        System.out.println(cache.stats().toString());
    }

}