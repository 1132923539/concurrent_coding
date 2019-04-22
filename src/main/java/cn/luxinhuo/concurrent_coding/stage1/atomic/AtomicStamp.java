package cn.luxinhuo.concurrent_coding.stage1.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicStampedReference;

@Slf4j
public class AtomicStamp {

    private static AtomicStampedReference<Boolean> stampedReference =
            new AtomicStampedReference<>(false, 0);

    public static void main1(String[] args) {

        // 期望值，即将赋给的值，期望版本号，修过后的版本号
        stampedReference.compareAndSet(false,true,0,1);
    }

    private static AtomicLongArray longArray=  new AtomicLongArray(100);

    public static void main(String[] args) {
        //数组下标，期望值，更新后的值
        longArray.compareAndSet(0,0,10);

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        int count = 0;
        for (int i = 0; i < 100; i++) {
            if(atomicBoolean.compareAndSet(false,true)){
                count++;
            }
        }
        //结果为 1
        log.info("执行了 {} 次", count);
    }
}
