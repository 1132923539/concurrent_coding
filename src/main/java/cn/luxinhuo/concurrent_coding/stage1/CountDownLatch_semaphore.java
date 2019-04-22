package cn.luxinhuo.concurrent_coding.stage1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CountDownLatch_semaphore {

    private static final int totalClient = 5000;
    private static final int maxThread = 200;

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(totalClient);
        Semaphore semaphore = new Semaphore(maxThread);

        for (int i = 0; i < totalClient; i++) {
            exec.submit(() -> {
                try {
                    semaphore.acquire();
                    count();
//                    Thread.sleep(10);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("execption: {}",e);
                }
                latch.countDown();
            });
        }
        latch.await();
        exec.shutdown();
        log.info("count: {}",count);

    }

    public static void count() {
//        count++;
        count.incrementAndGet();
    }
}
