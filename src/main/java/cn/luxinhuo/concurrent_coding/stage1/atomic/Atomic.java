package cn.luxinhuo.concurrent_coding.stage1.atomic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.*;

@Slf4j
public class Atomic {

    private static AtomicInteger aInt = new AtomicInteger();
    private static AtomicLong aLong = new AtomicLong();

    private static LongAdder longAdder =new LongAdder();

    private static AtomicBoolean aBoolean =new AtomicBoolean();

    private static final int totalClient = 5000;
    private static final int maxThread =30;
    public static void main1(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(totalClient);
        ExecutorService exec = Executors.newCachedThreadPool();
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
        log.info("count: {}",longAdder);

    }

    public static void count() {
//        count++;
        longAdder.increment();

        aBoolean.compareAndSet(false,true);
    }


    public static void main(String[] args){
        AtomicReference<Integer> ar = new AtomicReference<>(0);

        ar.compareAndSet(0,2);
        ar.compareAndSet(1,3);
        ar.compareAndSet(4,6);
        ar.compareAndSet(2,10);
        ar.compareAndSet(10,1000);

        // 结果为1000
        log.info("count: {}",ar.get());
    }


}
