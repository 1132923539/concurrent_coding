package cn.luxinhuo.concurrent_coding.stage1.AQS;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

@Slf4j
public class CountDownLatchLearn {

    private static final int threadCount = 200;
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum =i;
            exec.execute(()->{
                try {
                    threadNumPrint(threadNum);
                } catch (Exception e) {
                    log.error("exception",e);
                } finally {
                    latch.countDown();
                }
            });
        }
        // 设置超时时间,若超过10ms 则不等待，直接执行后面代码
        latch.await(10,TimeUnit.MILLISECONDS);
        log.info("finish");
        // 关闭线程池，节省资源
        exec.shutdown();
    }

    private static void threadNumPrint(int threadNum){
        log.info("{}----{}",threadNum,count.incrementAndGet());
    }
}
