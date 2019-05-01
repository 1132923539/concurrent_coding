package cn.luxinhuo.concurrent_coding.stage1.sync;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮流打印ABC
 */
public class TurnABC {

    private static Semaphore s0 =new Semaphore(1);
    private static Semaphore s1 =new Semaphore(0);
    private static Semaphore s2 =new Semaphore(0);

    private static AtomicInteger ai =new AtomicInteger(0);
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            new Thread(TurnABC::turnPrint).start();
        }
    }

    private static void turnPrint(){
            Thread t1 = new Thread(()->{
                for (int i = 0; i < 100 ; i++) {
                    try {
                        s0.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread()+"----A");
                    System.out.println(ai.incrementAndGet());
                    s1.release();
                }
            });

            Thread t2 = new Thread(()->{
                for (int i = 0; i < 100 ; i++){
                    try {
                        s1.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread()+"----B");
                    System.out.println(ai.incrementAndGet());
                    s2.release();
                }
            });

            Thread t3 = new Thread(()->{
                for (int i = 0; i < 100 ; i++){
                    try {
                        s2.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread()+"----C");
                    System.out.println(ai.incrementAndGet());
                    s0.release();
                }
            });

            t2.start();
            t3.start();
            t1.start();
        }
}
