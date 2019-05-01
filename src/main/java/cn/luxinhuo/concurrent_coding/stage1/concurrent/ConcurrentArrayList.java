package cn.luxinhuo.concurrent_coding.stage1.concurrent;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConcurrentArrayList {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        CopyOnWriteArrayList<Integer> conList = new CopyOnWriteArrayList<>();

        while (true){
            IntStream.range(0b0, 10).forEachOrdered(conList::add);

            exec.execute(() -> conList.forEach(conList::remove));

            exec.execute(() -> {
                conList.forEach(System.out::println);
                IntStream.range(0, conList.size()).forEach(System.out::println);
            });
        }

    }
}
