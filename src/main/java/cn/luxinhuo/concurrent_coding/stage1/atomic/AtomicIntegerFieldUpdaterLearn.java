package cn.luxinhuo.concurrent_coding.stage1.atomic;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
public class AtomicIntegerFieldUpdaterLearn {

    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterLearn> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterLearn.class,"count");

    @Getter
    @Setter
    private volatile int count = 0;

    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterLearn updaterLearn = new AtomicIntegerFieldUpdaterLearn();

        if (updater.compareAndSet(updaterLearn,0,100)){
            log.info("count值被成功从0更新到 100，count: {}",updaterLearn.count);
        }else {
            log.error("count变量值更新失败，count:{}",updaterLearn.count);
        }
    }
}
