package cn.luxinhuo.concurrent_coding.stage1.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
public class SyncCodeBlock {

    private void test1(){
        synchronized (this){
            for (int i = 0; i < 100; i++) {
                log.info("test1- i : {}",i);
            }
        }
    }

    private synchronized void test2(){
        for (int i = 0; i < 100; i++) {
            log.info("test2 i : {}",i);
        }
    }

    public static void main(String[] args) {
        SyncCodeBlock syncCodeBlock1 =new SyncCodeBlock();
        SyncCodeBlock syncCodeBlock2 = new SyncCodeBlock();

        Executor exec = Executors.newCachedThreadPool();

        //有序输出
        exec.execute(syncCodeBlock1::test1);
        exec.execute(syncCodeBlock1::test2);

        //无序输出
        exec.execute(syncCodeBlock1::test1);
        exec.execute(syncCodeBlock2::test2);
    }
}
