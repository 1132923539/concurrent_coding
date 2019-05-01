package cn.luxinhuo.concurrent_coding.stage1.AQS;

import com.sun.corba.se.spi.orbutil.threadpool.WorkQueue;

import java.util.concurrent.*;

public class FutureTaskAndFuture {

    public static void main1(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor myThreadPool = new ThreadPoolExecutor(10, 20, 60L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
        ExecutorService exec = Executors.newCachedThreadPool();

        Callable<String> c = ()->{
            System.out.println("进行了一系列的复杂操作");
            Thread.sleep(5000);
            return "Future的输出";
        };
        // 当主线程中future来接收 submit() 函数的返回值时，主线程就发生了阻塞，
        Future<String> future = exec.submit(c);

        System.out.println("使用future后的输出: "+ future.get());
        exec.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new ThreadPoolExecutor(10,20,60L,TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
        ExecutorService exec = Executors.newCachedThreadPool();

        Callable<String> c = ()->{
            System.out.println("进行了一系列的复杂操作1");
            Thread.sleep(5000);
            return "FutureTask的输出";
        };
        FutureTask<String> futureTask = new FutureTask<>(c);
        exec.submit(futureTask);

        System.out.println("主线程这里的代码可不被阻塞");
        // 当futureTask的 get() 方法被使用时可能会发生阻塞，因为futureTask内的任务不一定执行完成
        System.out.println("使用FutureTask在这里阻塞，因为这里使用到了futureTask"+futureTask.get());
        exec.shutdown();
    }
}
