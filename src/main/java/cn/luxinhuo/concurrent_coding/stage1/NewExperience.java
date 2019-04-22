package cn.luxinhuo.concurrent_coding.stage1;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class NewExperience {
    private static Map<Integer,Integer> map = new ConcurrentHashMap<>();

    Vector v = new Vector();

    private static Set<Integer> set =new ConcurrentSkipListSet<>();

    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    private  static AtomicInteger  count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch latch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    func(threadNum);
//                    Thread.sleep(10);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("execption", e);
                }
                latch.countDown();
            });
        }
        latch.await();
        //即使线程池关闭，线程池中未执行完成的线程任然在执行
        exec.shutdown();
        log.info("map size:{}",map.size());
        log.info("set size:{}",set.size());

        log.info("count:{}",count);
    }

    private static void func(int threadNum) {

        //这里多个线程操作map将会产生线程安全问题
        map.put(threadNum, threadNum);
        set.add(threadNum);
        count.addAndGet(1);
    }
}
