package cn.luxinhuo.concurrent_coding.stage1.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierLearn {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
        log.info("当有指定数量线程到达之后，这个线程将被执行");
    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1500);
            exec.execute(() -> {
                try {
                    race();
                } catch (InterruptedException | BrokenBarrierException e) {
                    log.error("exception", e);
                }
            });
        }
        exec.shutdown();
    }

    private static void race() throws BrokenBarrierException, InterruptedException {
        log.info("{} is ready", Thread.currentThread().getName());
        try {
            cyclicBarrier.await(100, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        log.info("{} had continue running  ",Thread.currentThread().getName());
    }

}
