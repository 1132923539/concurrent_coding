package cn.luxinhuo.concurrent_coding.stage1.thread_security;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class unsafeVector {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Vector<Integer> vector = new Vector<>();

        while (true){
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            exec.execute(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.remove(i);
                }
            });

            exec.execute(() -> {
                /**
                 * 这里的size先被读取后，执行到get这里前，cpu切换到其他线程，
                 * 后面一大批元素被删掉，从而导致get(i) 报出空指针异常
                  */
                for (int i = 0; i < vector.size(); i++) {
                    vector.get(i);
                }
            });
        }
    }
}
