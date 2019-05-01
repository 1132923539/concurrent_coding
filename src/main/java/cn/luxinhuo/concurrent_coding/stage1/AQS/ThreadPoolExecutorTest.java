package cn.luxinhuo.concurrent_coding.stage1.AQS;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorTest {
    private static AtomicInteger ai = new AtomicInteger(0);

    private static final ThreadPoolExecutor cachedThreadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    private static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
    private static final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    private static final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(30);

    public static void main1(String[] args) throws InterruptedException {


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 1000, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(1));

//        threadPoolExecutor.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 2000; i++) {
            threadPoolExecutor.submit(() -> {
                System.out.println("第" + ai.incrementAndGet() + "个线程,线程名称为：" + Thread.currentThread().getName());
            });
//            Thread.sleep(100);
            System.out.println("线程数量为：" + threadPoolExecutor.getPoolSize());
        }
    }

    public static void main2(String[] args) {
        // 延迟1000毫秒执行线程
        scheduledThreadPool.schedule(() -> System.out.println("延迟调度执行"), 1000, TimeUnit.MILLISECONDS);

        // 初始化时延时 1秒，后每隔3s执行一次
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            System.out.println("延迟1s,并以3s的时间间隔执行");
        }, 1, 3, TimeUnit.SECONDS);
    }

    public static void main(String[] args){

        // 使用timer实现定时任务
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("这是timer的定时任务，时间间隔为1s");
            }
        },new Date(),1000);
    }

}
