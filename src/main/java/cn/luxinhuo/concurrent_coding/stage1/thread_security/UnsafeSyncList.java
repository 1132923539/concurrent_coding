package cn.luxinhuo.concurrent_coding.stage1.thread_security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class UnsafeSyncList {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Integer> syncList = Collections.synchronizedList(new ArrayList<>());

        while (true){
            IntStream.range(0, 10).forEachOrdered(syncList::add);

            exec.execute(() -> IntStream.range(0, syncList.size()).forEach(syncList::remove));

            exec.execute(() -> {
                syncList.forEach(System.out::println);
                IntStream.range(0, syncList.size()).forEach(System.out::println);
            });
        }

    }
}
