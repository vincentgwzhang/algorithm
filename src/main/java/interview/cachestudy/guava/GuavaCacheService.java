package interview.cachestudy.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GuavaCacheService {

    public void setCache() throws Exception {
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                                                          //设置并发级别为8，并发级别是指可以同时写缓存的线程数
                                                          .concurrencyLevel(8)
                                                          //设置缓存容器的初始容量为10
                                                          .initialCapacity(10)
                                                          //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
                                                          .maximumSize(100)
                                                          //是否需要统计缓存情况,该操作消耗一定的性能,生产环境应该去除
                                                          .recordStats()
                                                          //设置写缓存后n秒钟过期
                                                          .expireAfterWrite(60, TimeUnit.SECONDS)
                                                          //设置读写缓存后n秒钟过期,实际很少用到,类似于expireAfterWrite
                                                          //.expireAfterAccess(17, TimeUnit.SECONDS)
                                                          //只阻塞当前数据加载线程，其他线程返回旧值
                                                          //.refreshAfterWrite(13, TimeUnit.SECONDS)
                                                          //设置缓存的移除通知
                                                          .removalListener(notification -> {
                                                              System.out.println(notification.getKey() + " " + notification.getValue() + " 被移除,原因:" + notification.getCause());
                                                          })
                                                          //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
                                                          .build(new DemoCacheLoader());

        CountDownLatch countDownLatch1 = new CountDownLatch(1);

        //模拟线程并发
        new Thread(() -> {
            //非线程安全的时间格式化工具
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            try {
                for (int i = 0; i < 3; i++) {
                    String value = cache.get(i);
                    System.out.println(Thread.currentThread().getName() + " " + simpleDateFormat.format(new Date()) + " " + value);
                    TimeUnit.SECONDS.sleep(1);
                }
                countDownLatch1.countDown();
            } catch (Exception ignored) {
            }
        }).start();

        countDownLatch1.await();
        TimeUnit.SECONDS.sleep(80);

        CountDownLatch countDownLatch2 = new CountDownLatch(1);

        new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            try {
                for (int i = 0; i < 2; i++) {
                    String value = cache.get(i);
                    System.out.println(Thread.currentThread().getName() + " " + simpleDateFormat.format(new Date()) + " " + value);
                    TimeUnit.SECONDS.sleep(1);
                }
                countDownLatch2.countDown();
            } catch (Exception ignored) {
            }
        }).start();
        //缓存状态查看

        countDownLatch2.await();

        System.out.println(cache.stats().toString());
    }

    public static class DemoCacheLoader extends CacheLoader<Integer, String> {
        @Override
        public String load(Integer key) throws Exception {
            System.out.println(Thread.currentThread().getName() + " load data for [" + key + "]");
            TimeUnit.SECONDS.sleep(1);
            Random random = new Random();
            System.out.println(Thread.currentThread().getName() + " load data for [" + key + "] finish");
            return "Key:" + key + ", value:" + random.nextInt(10000);
        }
    }

    @Test
    public void test1() throws Exception {
        setCache();
    }
}