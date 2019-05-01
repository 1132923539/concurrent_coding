package cn.luxinhuo.concurrent_coding.stage1.AQS;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Size;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SemaphoreLearn {

    private static final int threadCount = 200;
    private static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(()->{
                try {
                    semaphore.tryAcquire(2,10,TimeUnit.MILLISECONDS);
                    threadNumPrint(threadNum);
                    semaphore.release(3);
                } catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }
        // 关闭线程池，节省资源
        exec.shutdown();
    }

    private static void threadNumPrint(int threadNum) throws InterruptedException {
        Thread.sleep(300);
        log.info("{}",threadNum);

    }
}
